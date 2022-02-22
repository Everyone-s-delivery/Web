package everyone.delivery.demo.domain.post;

import everyone.delivery.demo.common.response.ResponseUtils;
import everyone.delivery.demo.domain.post.dtos.CreatePostDto;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
import everyone.delivery.demo.domain.post.dtos.UpdatePostDto;
import everyone.delivery.demo.security.user.dtos.UserDto;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@Api(tags = {"* 모집 글 API(사용자[모집자 또는 참여자] 권한)"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    @GetMapping("")
    @ApiOperation(value = "글 리스트 조회", notes = "https://keen-derby-c16.notion.site/e7c2f28d960445c7a84a8dc8ac15412f")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity getAllList(){
        return ResponseUtils.out(postService.getList());
    }


    @PostMapping("/page")
    @ApiOperation(value = "글 리스트 조회(페이징)", notes = "글 리스트를 조회할 수 있습니다. 검색조건에 따른 페이징을 할 수 있습니다." +
            "offset ~ limit+1 방식으로 무한 스크롤 구현합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity getPagedList(@Valid @RequestBody PostSearchDto postSearchDto){

        return ResponseUtils.out(postService.getPagedList(postSearchDto));
    }


    @GetMapping("{postId}")
    @ApiOperation(value = "글 개별 조회", notes = "postId에 해당하는 글을 조회할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity getById(@PathVariable @Min(value = 1, message = "postId cannot be minus.")Long postId){
        return ResponseUtils.out(postService.getById(postId));
    }

    @PostMapping("")
    @ApiOperation(value = "글 등록", notes = "https://keen-derby-c16.notion.site/a139eabf352a4ad0a3a157965fc41cd2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity create(
            @AuthenticationPrincipal UserDto tokenUserDto,
            @Valid @RequestBody @ApiParam(value = "모집 글 정보를 갖는 객체", required = true) CreatePostDto createPostDto){
        return ResponseUtils.out(postService.create(tokenUserDto.getUserId(), createPostDto));
    }

    @PutMapping("{postId}")
    @ApiOperation(value = "글 수정", notes = "https://keen-derby-c16.notion.site/6b0b77a8e3484edea921ec4643969717")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity update(
            @AuthenticationPrincipal UserDto tokenUserDto,
            @PathVariable @Min(value = 1, message = "postId cannot be minus.")Long postId,
            @Valid @RequestBody @ApiParam(value = "모집 글의 수정 정보를 갖는 객체", required = true) UpdatePostDto updatePostDto){
        return ResponseUtils.out(postService.update(tokenUserDto, postId, updatePostDto));
    }

    @DeleteMapping("/{postId}")
    @ApiOperation(value = "기존 모집 글 삭제",
            notes = "기존 덧글을 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity delete(
            @AuthenticationPrincipal UserDto tokenUserDto,
            @PathVariable @Min(value = 1, message = "postId cannot be minus.") Long postId) {
        return ResponseUtils.out(postService.delete(tokenUserDto, postId));
    }
}
