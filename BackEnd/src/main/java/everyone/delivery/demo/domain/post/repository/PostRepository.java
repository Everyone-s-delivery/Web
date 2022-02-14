package everyone.delivery.demo.domain.post.repository;

import everyone.delivery.demo.domain.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long>, PostQueryDSLRepository {


}
