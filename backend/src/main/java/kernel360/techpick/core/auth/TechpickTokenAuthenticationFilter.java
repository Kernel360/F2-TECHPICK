package kernel360.techpick.core.auth;

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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.config.SecurityConfig;
import kernel360.techpick.core.model.user.Role;
import kernel360.techpick.core.util.CookieUtil;
import kernel360.techpick.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TechpickTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getTokenFromCookie(request);

        if (jwtUtil.isValidToken(token)) {
            Authentication authentication = convertToAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            CookieUtil.clearAll(request, response);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        return findCookieByKey(cookies);
    }

    private String findCookieByKey(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(SecurityConfig.ACCESS_TOKEN_KEY)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private Authentication convertToAuthentication(String token) {

        Long id = jwtUtil.getUserId(token);
        Role role = jwtUtil.getRole(token);

        return new UsernamePasswordAuthenticationToken(
            id, token,
            List.of(new SimpleGrantedAuthority(role.toString()))
        );
    }
}
