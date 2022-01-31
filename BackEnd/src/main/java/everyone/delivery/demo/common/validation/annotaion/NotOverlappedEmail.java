package everyone.delivery.demo.common.validation.annotaion;

import everyone.delivery.demo.common.validation.validator.NotOverlappedEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD, ElementType.PARAMETER})          //파라미터, 필드에 붙을 수 있다는 의미
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotOverlappedEmailValidator.class)
public @interface NotOverlappedEmail {
    String message() default "Not allowed duplicated value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
