package kernel360.techpick.core.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kernel360.techpick.core.auth.TechpickLogoutHandler;
import kernel360.techpick.core.auth.TechpickOAuth2AuthorizationRequestRepository;
import kernel360.techpick.core.auth.TechpickOAuth2Service;
import kernel360.techpick.core.auth.TechpickOAuthSuccessHandler;
import kernel360.techpick.core.auth.TechpickTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	public static final String ACCESS_TOKEN_KEY = "access_token";
	public static final String REFRESH_TOKEN_KEY = "refresh_token";
	public static final String LOGIN_FLAG_FOR_FRONTEND = "techPickLogin";
	public static final String OAUTH_SUCCESS_RETURN_URL_TOKEN_KEY = "return_url";

	private final TechpickLogoutHandler techpickLogoutHandler;
	private final TechpickOAuthSuccessHandler techpickOAuthSuccessHandler;
	private final TechpickTokenAuthenticationFilter techpickTokenAuthenticationFilter;
	private final TechpickOAuth2Service	techpickOAuth2Service;
	private final TechpickOAuth2AuthorizationRequestRepository techpickOAuth2AuthorizationRequestRepository;

	@Value("${api.base-url}")
	private String baseUrl;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(cors -> cors
				.configurationSource(corsConfigurationSource())
			)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.logout(config -> config
				.addLogoutHandler(techpickLogoutHandler)
				.logoutSuccessHandler(techpickLogoutHandler)
				// .clearAuthentication(true)
			)
			.sessionManagement(management -> management
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilterBefore(
				techpickTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
			)
			.authorizeHttpRequests(request -> request
				.requestMatchers("/api-docs/**").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/api/login/**").permitAll()
				.anyRequest().authenticated()
			)
			.oauth2Login(oauth -> oauth
				.authorizationEndpoint(authorization -> authorization
					.baseUri("/api/login")
					.authorizationRequestRepository(techpickOAuth2AuthorizationRequestRepository)
				)
				.redirectionEndpoint(redirection -> redirection
					.baseUri("/api/login/oauth2/code/*")
				)
				.userInfoEndpoint(userInfo -> userInfo
					.userService(techpickOAuth2Service)
				)
				.successHandler(techpickOAuthSuccessHandler)
			);
		;
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.setAllowedOrigins(List.of(
			baseUrl, /* from env */
			"https://local.minlife.me:3000" /* Frontend Local */,
			"chrome-extension://nijonkmmpngclkmeddmgjgdhjefmnmbm" /* Chrome Extension */
		));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setExposedHeaders(List.of("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
