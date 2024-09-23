package kernel360.techpick.core.util;

import java.time.Duration;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kernel360.techpick.core.feature.user.User;

// 패키지 위치에 대한 고민 필요
@Component
public class JwtUtil {

	@Value("${spring.jwt.issuer}")
	private String issuer;
	@Value("${spring.jwt.secret}")
	private String secret;

	/*
		userId만 토큰에 포함됨
	 */
	public String getToken(User user, Duration expiry) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiry.toMillis());

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setIssuer(issuer)
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.claim("id", user.getId())
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		var claims = getClaims(token);
		return claims.get("id", Long.class);
	}

	private Claims getClaims(String token) {
		return Jwts.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token)
			.getBody();
	}
}
