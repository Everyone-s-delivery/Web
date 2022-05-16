package everyone.delivery.demo.domain.file;

import everyone.delivery.demo.common.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.AbstractMap;

/***
 * 원본파일 처리를 위한 컨트롤러
 */
@Api(tags = {"* 파일 처리 API(사용자[모집자 또는 참여자] 권한)"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/img")
    @ApiOperation(value = "이미지 파일 업로드", notes = "파일을 업로드 할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity upload(
            @RequestPart(value = "attachedFile", required=false) MultipartFile attachedFile) throws IOException {
        String serverFileName = fileService.saveMultipartFile(attachedFile);
        return ResponseUtils.out("serverFileName: " + serverFileName);
    }

    @GetMapping("/img/{serverFileName}")
    @ApiOperation(value = "이미지 파일 보기", notes = "서버에 업로드한 이미지 파일을 볼 수 있습니다.")
    public ResponseEntity display(
            @RequestHeader("User-Agent") String agent,
            @PathVariable String serverFileName){
        AbstractMap.SimpleEntry<Resource, String> resInfo = fileService.getFile(serverFileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(resInfo.getValue()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileService.convertFileName(serverFileName, agent) + "\"")
                .body(resInfo.getKey());
    }
}
