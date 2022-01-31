package everyone.delivery.demo;

import everyone.delivery.demo.common.exception.error.ErrorMap;
import everyone.delivery.demo.common.exception.error.FileError;
import everyone.delivery.demo.common.exception.error.RestError;
import org.reflections.Reflections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.EnumSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 활성화
public class DemoApplication {

	public static void main(String[] args) {
		setErrorMap();
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/***
	 * spring boot application 처음 기동 시 errorMap 세팅
	 * constraintViolation 에서 값을 넘겨주기 위해 사용
	 */
	private static void setErrorMap(){
		Reflections reflections = new Reflections("everyone.delivery.demo");
		Set<Class<? extends RestError>> subTypesOf = reflections.getSubTypesOf(RestError.class);
		for(Class clazz: subTypesOf){
			for(Object obj: EnumSet.allOf(clazz)){
				RestError error = (RestError) obj;
				ErrorMap.setError(error.toString(),error);
			}
		}
	}
}
