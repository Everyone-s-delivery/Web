package everyone.delivery.demo.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 설정
 * @author gshgsh0831
 * **/
@Configuration
//@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket docket() {
		ApiInfoBuilder apiInfo = new ApiInfoBuilder();
		apiInfo.title("API 서버 문서");
		apiInfo.description("API 서버 문서입니다.");
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfo.build());
		
		ApiSelectorBuilder apis = docket.
				select().
				apis(RequestHandlerSelectors.
						basePackage("everyone.delivery.demo"));
		apis.paths(PathSelectors.ant("/**"));
		return apis.build();
	}
}
