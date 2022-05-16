package everyone.delivery.demo.common.configuration;

import everyone.delivery.demo.security.CustomAccessDeniedHandler;
import everyone.delivery.demo.security.CustomAuthenticationEntryPoint;
import everyone.delivery.demo.security.JWT.JwtAuthenticationFilter;
import everyone.delivery.demo.security.JWT.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
				.cors().configurationSource(corsConfigurationSource()).and()
				.csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은
																							// 필요없으므로 생성안함.
				.and().authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
				.antMatchers(
						"/swagger-ui/**",
						"/signin",
						"/signup",
						"/JWTException/**",
						"/actuator",
						"/actuator/**",
						"/img/*",
						"/h2-console",
						"/h2-console/**",
						"/test",
						"/test/**")
				.permitAll() // 누구나 접근가능
				.antMatchers(HttpMethod.GET,
						"/posts",
						"/posts/**",
						"/file/img",
						"/file/img/**"
				).permitAll()
				.antMatchers("/users","/users/*").hasRole("ADMIN")
				.anyRequest().hasAnyRole("PARTICIPANTS", "RECRUITER", "ADMIN") // 그외 나머지 요청은 모두 인증된 회원(사용자[참여자 또는 모집자] + 관리자)만 접근 가능
				.and()
	            .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
				.and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.and().addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
						UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣는다
	}

	@Override // ignore check swagger resource
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
				"/swagger/**");

	}

	// CORS 허용 적용
	//https://gowoonsori.com/error/springsecurity-cors/
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOriginPattern("*");
//		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}