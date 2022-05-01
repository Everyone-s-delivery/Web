package everyone.delivery.demo.domain.post.repository;

import everyone.delivery.demo.common.request.dto.PagingRequestDto;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;

import java.util.List;

public interface PostQueryDSLRepository {

    List<PostEntity> getPagedList(PostSearchDto postSearchDto, PagingRequestDto pagingRequestDto);
}
