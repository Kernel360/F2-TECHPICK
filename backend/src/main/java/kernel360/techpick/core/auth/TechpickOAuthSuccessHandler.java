package kernel360.techpick.core.auth;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.config.SecurityConfig;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.core.model.user.UserRepository;
import kernel360.techpick.core.util.CookieUtil;
import kernel360.techpick.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TechpickOAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    // TODO: 이 시점은 이미 앞단에서 회원가입 처리까지 모두 끝난 시점이다...
    //       여기서 회원 가입 성공으로 처리되지 않는 방법 없을까 ??
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {

        var oAuth2UserInfo = (TechpickOAuth2UserInfo)authentication.getPrincipal();

        // TODO: custom exception 으로 변경 필요
        // TODO: custom user service + user details로 구현 진행
        // User user = userRepository.findBySocialProviderId(oAuth2UserInfo.getName())
        //                           .orElseThrow(/* TODO: change to custom exception */);

        // String accessToken = jwtUtil.createToken(user, ACCESS_TOKEN_DURATION);
        // int cookieMaxAge = (int)ACCESS_TOKEN_DURATION.toSeconds();

        // CookieUtil.deleteCookie(request, response, SecurityConfig.ACCESS_TOKEN_KEY);
        // CookieUtil.addCookie(response, SecurityConfig.ACCESS_TOKEN_KEY, accessToken, cookieMaxAge);

        // 임시 저장해둔
        CookieUtil.findCookie(request.getCookies(), SecurityConfig.OAUTH_SUCCESS_RETURN_URL_TOKEN_KEY)
                  .ifPresent((cookie -> {
                      try {
                          CookieUtil.deleteCookie(request, response, SecurityConfig.OAUTH_SUCCESS_RETURN_URL_TOKEN_KEY);
                          response.sendRedirect(cookie.getValue());
                      } catch (IOException e) {
                          // TODO: surround with custom api exception
                          throw new RuntimeException(e);
                      }
                  }));

        super.clearAuthenticationAttributes(request);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
