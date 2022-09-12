package shop.makaroni.bunjang.src;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration    // 스프링 실행시 설정파일 읽어드리기 위한 어노테이션
@EnableSwagger2    // Swagger2를 사용하겠다는 어노테이션
@SuppressWarnings("unchecked")    // warning밑줄 제거를 위한 태그
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
				.paths(PathSelectors.ant("/**"))
				.build();
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				.displayRequestDuration(true)
				.validatorUrl("")
				.build();
	}
}
