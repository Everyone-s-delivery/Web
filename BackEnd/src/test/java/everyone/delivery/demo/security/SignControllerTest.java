package everyone.delivery.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import everyone.delivery.demo.security.user.dtos.CreateUserDto;
import everyone.delivery.demo.security.user.dtos.LoginUserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * spring controller test 참고: https://tech.devgd.com/12 spring boot + test용 h2
 * db사용 + mybatis 참고:
 * https://atoz-develop.tistory.com/entry/Spring-Boot-MyBatis-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95
 * https://re-coder.tistory.com/5
 **/
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)	//@Test가 붙은 메소드를 실행할 때 마다가 아니라 모든 테스트에 대해 하나의 인스턴스만 만든다.
public class SignControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 테스트 시작 전에 회원 한명을 등록
	 * **/
	@BeforeAll
	void init() throws Exception {
		CreateUserDto createUserDto = CreateUserDto.builder().email("userTest1111@gmail.com").password("Rnjs@1q2w3e")
				.nickName(UUID.randomUUID().toString())
				.address(UUID.randomUUID().toString())
				.build();
		String jsonUserNormal = objectMapper.writeValueAsString(createUserDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content(jsonUserNormal)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	/**
	 * 로그인 성공 테스트
	 **/
	@Test
	public void signinSucessTest() throws Exception{
		LoginUserDto loginUserDto = LoginUserDto.builder().email("userTest1111@gmail.com").password("Rnjs@1q2w3e").build();
		String jsonUserLogin = objectMapper.writeValueAsString(loginUserDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/signin")
				.content(jsonUserLogin)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	/**
	 * 로그인 실패 테스트 => 요청 메세지 바디가 없는 경우
	 **/
	@Test
	public void signinNoRequestBodyFailTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/signin")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print());
	}

	/**
	 * 로그인 실패 테스트 => 없는 이메일로 로그인
	 **/
	@Test
	public void signinEmailFailTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/signin")
				.content("email=xxxxx@gmail.com&password=Rnjs@123456789")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.msg").value("login fail, check email"))
				.andDo(print());
	}

	/**
	 * 로그인 실패 테스트 => 없는 비밀번호로 로그인
	 **/
	@Test
	public void signinPassowrdFailTest() throws Exception {


		mockMvc.perform(MockMvcRequestBuilders.post("/signin")
				.content("email=gshgsh0831@gmail.com&password=Rnjs@zzz")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.msg").value("login fail, check password"))
				.andDo(print());
	}

	/**
	 * 회원가입 성공 테스트
	 * 
	 **/
	@Test
	public void signupSucessTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content("email=gshgsh1111@gmail.com&password=Rnjs@123456789")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	/**
	 * 회원가입 실패 테스트 => 중복된 이메일로 회원가입
	 **/
	@Test
	public void signupFailTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content("email=gshgsh2222@gmail.com&password=Rnjs@123456789")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
				.content("email=gshgsh2222@gmail.com&password=Rnjs@123456789")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized())
				.andDo(print());
	}

}
