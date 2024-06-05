package datalab.reinfect.tour.http.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import datalab.reinfect.tour.enums.UniqueType;
import datalab.reinfect.tour.http.validators.ServerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.RequiredArgsConstructor;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MemberUniqueCheck.Validator.class)
public @interface MemberUniqueCheck {
	
	String message() default "이미 사용 중인 정보입니다.";
	
	UniqueType type();
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
	@RequiredArgsConstructor
	class Validator implements ConstraintValidator<MemberUniqueCheck, String> {
		
		private UniqueType type;
		
		private final ServerValidator validator;
		
		@Override
        public void initialize(MemberUniqueCheck constraintAnnotation) {
			type = constraintAnnotation.type();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return validator.memberUniqueCheck(type, value);
        }
		
	}

}
