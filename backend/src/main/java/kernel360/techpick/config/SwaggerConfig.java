package kernel360.techpick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
			.title("TechPick API")
			.description("TechPick API 명세서")
			.version("1.0.0");
	}

	// 이후 시큐리티 적용 후 추가 설정 필요
}
