package everyone.delivery.demo.security.user;

import everyone.delivery.demo.common.response.ResponseUtils;
import everyone.delivery.demo.security.user.dtos.CreateUserDto;
import everyone.delivery.demo.security.user.dtos.UpdateUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@Api(tags = {"* 사용자 API(어드민 권한)"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final CustomUserDetailService customUserDetailService;

	/**
	 * 전체 사용자 리스트 리턴
	 * @return
	 * **/
	@GetMapping("")
	@ApiOperation(value = "사용자 목록 조회", notes = "전체 사용자 리스트를 반환합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(관리자 토큰)", required = false, dataType = "String", paramType = "header")
	})
	public ResponseEntity getList(){
		return ResponseUtils.out(customUserDetailService.getList());
	}
	
	
	/**
	 * {userId}에 해당하는 사용자 리턴, 없으면 null리턴
	 * @param userId
	 * @return
	 * **/
	@GetMapping("/{userId}")
	@ApiOperation(value = "사용자 상세 조회",
	notes = "사용자 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(관리자 토큰)", required = true, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "userId",value = "사용자 번호(1 이상의 값)", example = "1" )
	})
	public ResponseEntity get(@PathVariable @Min(value = 1, message = "userId cannot be minus.") Long userId) {
		return ResponseUtils.out(customUserDetailService.getById(userId));
	}
	
//	/**
//	 * 하나의 사용자 등록, 등록된 사용자의 userId리턴
//	 * @param userDto
//	 * @return
//	 * **/
//	@PostMapping("")
//	@ApiOperation(value = "신규 사용자 등록", notes = "신규 사용자를 등록합니다.")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(관리자 토큰)", required = false, dataType = "String", paramType = "header")
//	})
//	public SingleResult<Long> save(@RequestBody BasicUserDto userDto) {
//		return responseService.getSingleResult(customUserDetailService.save(userDto));
//	}

	
	/**
	 * {userId}에 해당하는 사용자 수정, 수정된 사용자의 userId리턴
	 * @param userId, user
	 * @return
	 * **/
	@PutMapping("/{userId}")
	@ApiOperation(value = "기존 사용자 수정", 
	notes = "기존 사용자를 수정합니다. 사용자 아이디는 path로 넘기고 나머지 정보를 json body로 넘겨야 합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(관리자 토큰)", required = true, dataType = "String", paramType = "header")
	})
	public ResponseEntity update(@PathVariable @Min(value = 1, message = "userId cannot be minus.") Long userId,
								 @Valid @RequestBody UpdateUserDto updateUserDto) {
		return ResponseUtils.out(customUserDetailService.update(userId ,updateUserDto));
	}
	
	/**
	 * {userId}에 해당하는 사용자 삭제, 삭제된 사용자의 userId리턴
	 * @param userId
	 * @return
	 * **/
	@DeleteMapping("/{userId}")
	@ApiOperation(value = "기존 사용자 삭제", 
	notes = "기존 사용자를 삭제합니다. path로 삭제하고자 하는 사용자의 번호를 넘겨야 합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(관리자 토큰)", required = true, dataType = "String", paramType = "header")
	})
	public ResponseEntity delete(@PathVariable @Min(value = 1, message = "userId cannot be minus.") Long userId) {
		return ResponseUtils.out(customUserDetailService.delete(userId));
	}
}