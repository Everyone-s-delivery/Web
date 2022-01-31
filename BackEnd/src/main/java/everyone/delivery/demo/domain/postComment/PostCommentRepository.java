package everyone.delivery.demo.domain.postComment;

import everyone.delivery.demo.domain.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Long> {
}
