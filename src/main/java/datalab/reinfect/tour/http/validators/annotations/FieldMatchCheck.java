package datalab.reinfect.tour.http.validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

/**
 * 두 값의 일치 여부를 확인하는 사용자 정의 유효성 검사
 * * DTO 클래스에 적용하여 사용함.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchCheck.Validator.class)
public @interface FieldMatchCheck {

    String message() default "두 값이 서로 일치하지 않습니다.";

    String firstField();
    String secondField();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<FieldMatchCheck, Object> {

        private String firstField;
        private String secondField;
        private String message;

        @Override
        public void initialize(FieldMatchCheck constraintAnnotation) {
            firstField = constraintAnnotation.firstField();
            secondField = constraintAnnotation.secondField();
            message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            boolean isValid = true;

            try {
                final Object firstObject = new BeanWrapperImpl(value).getPropertyValue(firstField);
                final Object secondObject = new BeanWrapperImpl(value).getPropertyValue(secondField);

                isValid = ((firstObject == null && secondObject == null) || (Objects.equals(firstObject, secondObject)));
            } catch (final Exception ignore) { }

            if (!isValid) {
                context.buildConstraintViolationWithTemplate(message)
                       .addPropertyNode(firstField)
                       .addConstraintViolation()
                       .disableDefaultConstraintViolation();
            }

            return isValid;
        }

    }

}
