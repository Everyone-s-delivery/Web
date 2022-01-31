package everyone.delivery.demo.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author gshgsh0831
 * **/
public interface UserRepository extends JpaRepository<UserEntity,Long> {
	
	List<UserEntity> findAll();
	Optional<UserEntity> findByUserId(Long userId);
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByNickName(String nickName);


	/***
	 * 업데이트 기능도 있음(동일한 키가 있으면 업데이트)
	 * @param userEntity
	 * @return
	 */
	UserEntity save(UserEntity userEntity);
	

	void deleteByUserId(Long userId);
}
