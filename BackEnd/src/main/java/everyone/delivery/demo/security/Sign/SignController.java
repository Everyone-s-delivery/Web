package everyone.delivery.demo.security.Sign;

import everyone.delivery.demo.common.response.ResponseUtils;
import everyone.delivery.demo.security.user.dtos.CreateUserDto;
import everyone.delivery.demo.security.user.dtos.LoginUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = { "* 회원가입 & 로그인 API" })
@RequiredArgsConstructor
@RestController
@Validated
public class SignController {
	private final SignService signService;

//	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
//	@PostMapping(value = "/signin")
//	public ResponseEntity signin(@ApiParam(value = "이메일", required = true) @RequestParam("email") @NotNull(message = "email should be included") String email,
//								 @ApiParam(value = "비밀번호", required = true) @RequestParam("password") @NotNull(message = "password should be included") String password) {
//		return ResponseUtils.out(signService.signin(email,password));
//	}

	@ApiOperation(value = "로그인", notes = "https://keen-derby-c16.notion.site/3d0b8eb1e4f3410f9337d7d4e76e90cf")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@Valid @RequestBody @ApiParam(value = "회원 한 명의 로그인 정보를 갖는 객체", required = true) LoginUserDto loginUserDto) {
		return ResponseUtils.out(signService.signin(loginUserDto.getEmail(),loginUserDto.getPassword()));
	}

	@ApiOperation(value = "가입", notes = "https://keen-derby-c16.notion.site/e781aa02a1614a78b20f243d47399d1c")
	@PostMapping(value = "/signup")
	public ResponseEntity  signup(@Valid @RequestBody @ApiParam(value = "회원 한 명의 정보를 갖는 객체", required = true) CreateUserDto createUserDto) {
		return ResponseUtils.out(signService.signup(createUserDto));
	}



}