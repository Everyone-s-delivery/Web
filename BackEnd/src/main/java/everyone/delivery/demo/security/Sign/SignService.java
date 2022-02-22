package everyone.delivery.demo.security.Sign;

import everyone.delivery.demo.common.exception.ExceptionUtils;
import everyone.delivery.demo.common.exception.LogicalRuntimeException;
import everyone.delivery.demo.common.exception.error.UserError;
import everyone.delivery.demo.security.JWT.JwtTokenProvider;
import everyone.delivery.demo.security.Sign.model.TokenResult;
import everyone.delivery.demo.security.user.UserEntity;
import everyone.delivery.demo.security.user.UserRepository;
import everyone.delivery.demo.security.user.UserRole;
import everyone.delivery.demo.security.user.dtos.CreateUserDto;
import everyone.delivery.demo.security.user.dtos.UserDto;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    /***
     * 로그인
     * @param email
     * @param password
     * @return
     */
    public TokenResult signin(String email, String password) {
        Optional<UserEntity> findUserEntityOp = userRepository.findByEmail(email);
        UserEntity findUserEntity = ExceptionUtils.ifNullThrowElseReturnVal(
                UserError.INVALID_USER_ID, findUserEntityOp,"login fail, check email. email: {}", email);

        if (!passwordEncoder.matches(password, findUserEntity.getPassword())){
            log.error("login fail, check password. password: {}", password);
            throw new LogicalRuntimeException(UserError.INVALID_PASSWORD);
        }

        return new TokenResult(
                jwtTokenProvider.createToken(String.valueOf(findUserEntity.getEmail()), findUserEntity.getRoles()),findUserEntity.getUserId());
    }

    /***
     * 회원가입
     * @param createUserDto
     * @return
     */
    public UserDto signup(CreateUserDto createUserDto) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ROLE_PARTICIPANTS);
        roles.add(UserRole.ROLE_RECRUITER);
        if(createUserDto.getEmail().equals("admin@admin.com"))
            roles.add(UserRole.ROLE_ADMIN);
        if(createUserDto.getEmail().equals("admin1@admin1"))
            roles.add(UserRole.ROLE_ADMIN);

        UserEntity userEntity = convertDTOToEntity(createUserDto,roles);

        userEntity = userRepository.save(userEntity);
        return userEntity.toDTO();
    }

    public UserEntity convertDTOToEntity(CreateUserDto basicUserDto, Set<UserRole> roles){
        return UserEntity.builder()
                .email(basicUserDto.getEmail())
                .nickName(basicUserDto.getNickName())
                .password(passwordEncoder.encode(basicUserDto.getPassword()))
                .address(basicUserDto.getAddress())
                .roles(roles).build();
    }
}
