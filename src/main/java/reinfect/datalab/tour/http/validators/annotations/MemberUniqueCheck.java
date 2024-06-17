package reinfect.datalab.tour.http.validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.RequiredArgsConstructor;
import reinfect.datalab.tour.enums.MemberType;
import reinfect.datalab.tour.http.validators.ServerValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MemberUniqueCheck.Validator.class)
public @interface MemberUniqueCheck {

    String message();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    MemberType dataType();

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<MemberUniqueCheck, String> {

        private MemberType type;
        private final ServerValidator validator;

        @Override
        public void initialize(MemberUniqueCheck constraintAnnotation) {
            type = constraintAnnotation.dataType();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return validator.isUnique(type, value);
        }

    }

}
