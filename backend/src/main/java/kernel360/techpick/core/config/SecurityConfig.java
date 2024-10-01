package kernel360.techpick.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kernel360.techpick.core.auth.Filter.TokenAuthenticationFilter;
import kernel360.techpick.core.auth.handler.OAuth2SuccessHandler;
import kernel360.techpick.core.auth.service.CustomOAuth2Service;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOAuth2Service customOAuth2Service;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;
	private final TokenAuthenticationFilter tokenAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// TODO: 이후 설정 추가 필요
		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.logout(AbstractHttpConfigurer::disable)
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			// TokenAuthenticationFilter 를 UsernamePasswordAuthenticationFilter 앞에 추가
			.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(
				authRequest -> authRequest
					.requestMatchers("/api-docs/**").permitAll()
					.requestMatchers("/swagger-ui/**").permitAll()
					.requestMatchers("/api/login/**").permitAll()
					.anyRequest().authenticated()
			)
			.oauth2Login(
				oauth -> oauth
					.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2Service))
					.successHandler(oAuth2SuccessHandler)
			)
		;
		return http.build();
	}

}
