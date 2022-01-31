package everyone.delivery.demo.common.validation.validator;

import everyone.delivery.demo.common.exception.error.UserError;
import everyone.delivery.demo.common.validation.annotaion.NotOverlappedEmail;
import everyone.delivery.demo.security.user.UserEntity;
import everyone.delivery.demo.security.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class NotOverlappedEmailValidator implements ConstraintValidator<NotOverlappedEmail,String> {

    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        //에러 이넘 값을 넘길 시 반드시 기본 violation을 꺼야 한다.
        context.disableDefaultConstraintViolation();

        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){    //존재한다 => email은 중복된다 => 검증 오류
            log.error("email overlap. email: {}", email);
            context.buildConstraintViolationWithTemplate(UserError.OVERLAP_EMAIL.name())
                    .addConstraintViolation();
            return false;
        }
        else
            return true;
    }
}
