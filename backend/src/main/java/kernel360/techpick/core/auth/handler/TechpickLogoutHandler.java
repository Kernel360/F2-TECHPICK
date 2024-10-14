package kernel360.techpick.core.auth.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.config.SecurityConfig;
import kernel360.techpick.core.util.CookieUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechpickLogoutHandler implements LogoutHandler {

    @Override
    public void logout(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) {
        CookieUtil.deleteCookie(request, response, SecurityConfig.ACCESS_TOKEN_KEY);
        CookieUtil.deleteCookie(request, response, "JSESSIONID");
    }
}
