package everyone.delivery.demo.domain.post.repository;

import everyone.delivery.demo.common.request.dto.KeyColumn;
import everyone.delivery.demo.common.request.dto.OrderBy;
import everyone.delivery.demo.common.request.dto.PagingRequestDto;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
import org.springframework.data.domain.Slice;

public interface PostQueryDSLRepository {

    Slice<PostEntity> getPagedList(PostSearchDto postSearchDto, PagingRequestDto pagingRequestDto);
}
