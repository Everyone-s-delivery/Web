package everyone.delivery.demo.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import everyone.delivery.demo.security.dtos.CreateUserTESTDto;
import everyone.delivery.demo.security.user.dtos.CreateUserDto;
import org.json.simple.parser.JSONParser;

import org.json.simple.JSONObject;

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

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * spring controller test 참고: https://tech.devgd.com/12 spring boot + test용 h2
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
		CreateUserTESTDto createUserTESTDto = CreateUserTESTDto.builder().email("userTest1111@gmail.com").password("Rnjs@1q2w3e")
				.nickName("nickName1")
				.address(UUID.randomUUID().toString())
				.build();
		jsonUserNormal = objectMapper.writeValueAsString(createUserTESTDto);

		createUserTESTDto = CreateUserTESTDto.builder().password("Rnjs@1q2w3e").build();
		jsonUserEmailNull = objectMapper.writeValueAsString(createUserTESTDto);

		createUserTESTDto = CreateUserTESTDto.builder().email("userTest1111@gmail.com").build();
		jsonUserPasswordNull = objectMapper.writeValueAsString(createUserTESTDto);

		createUserTESTDto = CreateUserTESTDto.builder().email("userTest1111@gmail.com").password("Rnjs@1q2w3e").build();
		jsonUserRoleNull = objectMapper.writeValueAsString(createUserTESTDto);

		// 회원가입
		createUserTESTDto = CreateUserTESTDto.builder().email("admin@admin.com").password("Rnjs@123456789")
				.nickName(UUID.randomUUID().toString())
				.address(UUID.randomUUID().toString())
				.build();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/signup").content(objectMapper.writeValueAsString(createUserTESTDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		// 로그인
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/signin").content(objectMapper.writeValueAsString(createUserTESTDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		testAdminToken = getJWTToken(mvcResult.getResponse().getContentAsString());

		// 회원가입
		createUserTESTDto = CreateUserTESTDto.builder().email("userTest0831@gmail.com").password("Rnjs@123456789")
				.nickName(UUID.randomUUID().toString())
				.address(UUID.randomUUID().toString())
				.build();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/signup").content(objectMapper.writeValueAsString(createUserTESTDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		// 로그인
		mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/signin").content(objectMapper.writeValueAsString(createUserTESTDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		testUserToken = getJWTToken(mvcResult.getResponse().getContentAsString());

		// 회원가입
		createUserTESTDto = CreateUserTESTDto.builder().email("userTest123123@gmail.com").password("Rnjs@123456789")
				.nickName(UUID.randomUUID().toString())
				.address(UUID.randomUUID().toString())
				.build();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/signup").content(objectMapper.writeValueAsString(createUserTESTDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// 로그인
		mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/signin").content(objectMapper.writeValueAsString(createUserTESTDto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getListTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users").header("Authorization", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

		mockMvc.perform(MockMvcRequestBuilders.get("/users").header("Authorization", testUserToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection()).andDo(print());

		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is3xxRedirection()).andDo(print());
	}

	@Test
	public void getTest() throws Exception {
		// 정상 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/2").header("Authorization", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andDo(print())
				.andExpect(jsonPath("$.userId").value("2"));

		// 음수 값을 같는 게시글 번호로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/-1").header("Authorization", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"));

		// 없는 게시글 번호로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/99999").header("Authorization", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.errorCode").value("INVALID_DATA"));

		// 사용자 토큰으로 요청
		mockMvc.perform(MockMvcRequestBuilders.get("/users/2").header("Authorization", testUserToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection()).andDo(print());
	}

	@Test
	public void updateTest() throws Exception {
		// 정상 요청
		mockMvc.perform(MockMvcRequestBuilders.put("/users/3").header("Authorization", testAdminToken)
				.content(jsonUserNormal).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

		// 이상 요청 => user path로 음수를 넘긴 경우
		mockMvc.perform(MockMvcRequestBuilders.put("/users/-11").header("Authorization", testAdminToken)
				.content(jsonUserNormal).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());

		// 이상 요청 => user path로 없는 게시글 번호를 넘긴 경우
		mockMvc.perform(MockMvcRequestBuilders.put("/users/9999").header("Authorization", testAdminToken)
				.content(jsonUserNormal).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void deleteTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/3").header("Authorization", testUserToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection())
				.andDo(print());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/3").header("Authorization", testAdminToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("3"));
	}

	private String getJWTToken(String response) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(response);
		return (String) jsonObj.get("token");
	}

}
