package kernel360.techpick.core.auth.handler;

import java.io.IOException;
import java.time.Duration;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.auth.model.OAuth2UserInfo;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.core.util.CookieUtil;
import kernel360.techpick.core.util.JwtUtil;
import kernel360.techpick.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
	private static final String ACCESS_TOKEN_KEY = "access_token";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

		var oAuth2UserInfo = (OAuth2UserInfo)authentication.getPrincipal();
		// TODO: custom exception 으로 변경 필요
		User user = userRepository.findBySocialProviderId(oAuth2UserInfo.getName()).orElseThrow();

		String accessToken = jwtUtil.getToken(user, ACCESS_TOKEN_DURATION);
		addAccessTokenToCookie(request, response, accessToken);

		// front 로컬 로그인 테스트를 위한 하드코딩
		// TODO: 배포 이후 수정예정
		response.sendRedirect("http://localhost:3000/login");

		super.clearAuthenticationAttributes(request);
		super.onAuthenticationSuccess(request, response, authentication);

	}

	private void addAccessTokenToCookie(HttpServletRequest request, HttpServletResponse response,
		String refreshToken) {

		int cookieMaxAge = (int)ACCESS_TOKEN_DURATION.toSeconds();

		CookieUtil.deleteCookie(request, response, ACCESS_TOKEN_KEY);
		CookieUtil.addCookie(response, ACCESS_TOKEN_KEY, refreshToken, cookieMaxAge);
	}
}
