package everyone.delivery.demo.domain.post.repository;

import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
import org.springframework.data.domain.Slice;

public interface PostQueryDSLRepository {

    Slice<PostEntity> getPagedList(PostSearchDto postSearchDto);
}
