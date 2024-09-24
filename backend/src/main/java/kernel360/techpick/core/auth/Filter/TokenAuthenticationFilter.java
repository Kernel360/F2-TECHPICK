package kernel360.techpick.core.auth.Filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.feature.user.Role;
import kernel360.techpick.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private static final String ACCESS_TOKEN_KEY = "access_token";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String token = getTokenFromCookie(request);

		if (jwtUtil.isValidToken(token)) {
			var authentication = convertToAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String getTokenFromCookie(HttpServletRequest request) {
		var cookies = request.getCookies();
		if (cookies != null) {
			for (var cookie : cookies) {
				if (cookie.getName().equals(ACCESS_TOKEN_KEY)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	private Authentication convertToAuthentication(String token) {

		Long id = jwtUtil.getUserId(token);
		Role role = jwtUtil.getRole(token);

		return new UsernamePasswordAuthenticationToken(
			id,
			token,
			List.of(new SimpleGrantedAuthority(role.toString()))
		);
	}
}
