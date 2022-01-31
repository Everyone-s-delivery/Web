package everyone.delivery.demo.security;

import everyone.delivery.demo.common.exception.LogicalRuntimeException;
import everyone.delivery.demo.common.exception.error.CommonError;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/JWTException")
@ApiIgnore
public class ExceptionController {

	@GetMapping(value = "/EmptyJWTToken")
	public void entrypointException() {
		throw new LogicalRuntimeException(CommonError.NOT_INCLUDE_JWT_TOKEN);
	}
	
	@GetMapping(value = "/accessdenied")
	public void accessdeniedException() {
		throw new LogicalRuntimeException(CommonError.UNAUTHORIZED_JWT_TOKEN);
	}
}