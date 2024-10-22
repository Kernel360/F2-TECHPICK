package kernel360.techpick.core.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.util.CookieUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechpickLogoutHandler implements LogoutHandler, LogoutSuccessHandler {

    @Override
    public void logout(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) {
        CookieUtil.clearAll(request, response);
    }

    @Override
    public void onLogoutSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) {
        // do not redirect
        authentication.setAuthenticated(false);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}