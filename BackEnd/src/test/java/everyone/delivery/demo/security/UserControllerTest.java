package everyone.delivery.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import everyone.delivery.demo.security.user.dtos.CreateUserDto;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * spring controller test 참고: https://tech.devgd.com/12 spring boot + test용 h2
 * db사용 + mybatis 참고:
 * https://atoz-develop.tistory.com/entry/Spring-Boot-MyBatis-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95
 * https://re-coder.tistory.com/5
 **/
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // @Test가 붙은 메소드를 실행할 때 마다가 아니라 모든 테스트에 대해 하나의 인스턴스만 만든다.
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private String testAdminToken;
	private String testUserToken;
	private String jsonUserNormal;
	private String jsonUserEmailNull;
	private String jsonUserPasswordNull;
	private String jsonUserRoleNull;

	@BeforeAll
	void initAll() throws Exception {
		// 정상 요청
		CreateUserDto createUserDto = CreateUserDto.builder().email("userTest1111@gmail.com").password("Rnjs@1q2w3e")
				.nickName("nickName1")
				.build();
		jsonUserNormal = objectMapper.writeValueAsString(createUserDto);

		createUserDto = CreateUserDto.builder().password("Rnjs@1q2w3e").build();
		jsonUserEmailNull = objectMapper.writeValueAsString(createUserDto);

		createUserDto = CreateUserDto.builder().email("userTest1111@gmail.com").build();
		jsonUserPasswordNull = objectMapper.writeValueAsString(createUserDto);

		createUserDto = CreateUserDto.builder().email("userTest1111@gmail.com").password("Rnjs@1q2w3e").build();
		jsonUserRoleNull = objectMapper.writeValueAsString(createUserDto);

		// 회원가입
		mockMvc.perform(MockMvcRequestBuilders.post("/signup").content("email=admin@1234.com&password=Rnjs@123456789")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
		// 로그인
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/signin").content("email=admin@1234.com&password=Rnjs@123456789")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
		testAdminToken = getJWTToken(mvcResult.getResponse().getContentAsString());

		// 회원가입
		mockMvc.perform(
				MockMvcRequestBuilders.post("/signup").content("email=userTest0831@gmail.com&password=Rnjs@123456789")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());
		// 로그인
		mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/signin").content("email=userTest0831@gmail.com&password=Rnjs@123456789")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
		testUserToken = getJWTToken(mvcResult.getResponse().getContentAsString());

		// 회원가입
		mockMvc.perform(
				MockMvcRequestBuilders.post("/signup").content("email=userTest123123@gmail.com&password=Rnjs@123456789")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());
		// 로그인
		mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/signin").content("email=userTest123123@gmail.com&password=Rnjs@123456789")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getListTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users").header("X-AUTH-TOKEN", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

		mockMvc.perform(MockMvcRequestBuilders.get("/users").header("X-AUTH-TOKEN", testUserToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection()).andDo(print());

		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is3xxRedirection()).andDo(print());
	}

	@Test
	public void getTest() throws Exception {
		// 정상 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/2").header("X-AUTH-TOKEN", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andDo(print())
				.andExpect(jsonPath("$.data.userId").value("2"));

		// 음수 값을 같는 게시글 번호로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/-1").header("X-AUTH-TOKEN", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.msg").value("userId cannot be minus."));

		// 없는 게시글 번호로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/99999").header("X-AUTH-TOKEN", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.msg").value("There is no corresponding information for userId."));

		// 없는 게시글 번호로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/99999").header("X-AUTH-TOKEN", testUserToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection()).andDo(print());
	}

	@Test
	public void saveTest() throws Exception {
		// 정상 요청
		mockMvc.perform(MockMvcRequestBuilders.post("/users").header("X-AUTH-TOKEN", testAdminToken)
				.content(jsonUserNormal).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());

		// 이상 요청 => email이 null일때
		mockMvc.perform(MockMvcRequestBuilders.post("/users").header("X-AUTH-TOKEN", testAdminToken)
				.content(jsonUserEmailNull).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"success\":false,\"code\":-1,\"msg\":\"Not enough user data.\"}"));

		// 이상 요청 => password가 null일때
		mockMvc.perform(MockMvcRequestBuilders.post("/users").header("X-AUTH-TOKEN", testAdminToken)
				.content(jsonUserPasswordNull).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(content().string("{\"success\":false,\"code\":-1,\"msg\":\"Not enough user data.\"}"));
	}

	@Test
	public void updateTest() throws Exception {
		// 정상 요청
		mockMvc.perform(MockMvcRequestBuilders.put("/users/3").header("X-AUTH-TOKEN", testAdminToken)
				.content(jsonUserNormal).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"success\":true,\"code\":0,\"msg\":\"성공하였습니디.\",\"data\":3}"))
				.andDo(print());

		// 이상 요청 => user path로 음수를 넘긴 경우
		mockMvc.perform(MockMvcRequestBuilders.put("/users/-11").header("X-AUTH-TOKEN", testAdminToken)
				.content(jsonUserNormal).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"success\":false,\"code\":-1,\"msg\":\"userId cannot be minus.\"}"))
				.andDo(print());

		// 이상 요청 => user path로 없는 게시글 번호를 넘긴 경우
		mockMvc.perform(MockMvcRequestBuilders.put("/users/9999").header("X-AUTH-TOKEN", testAdminToken)
				.content(jsonUserNormal).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string(
						"{\"success\":false,\"code\":-1,\"msg\":\"There is no corresponding information for userId.\"}"));
	}
	
	@Test
	public void deleteTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/3").header("X-AUTH-TOKEN", testUserToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection())
				.andDo(print());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/3").header("X-AUTH-TOKEN", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("{\"success\":true,\"code\":0,\"msg\":\"성공하였습니디.\",\"data\":3}"));
	}

	private String getJWTToken(String response) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(response);
		jsonObj = (JSONObject) jsonObj.get("data");
		return (String) jsonObj.get("token");
	}

}
