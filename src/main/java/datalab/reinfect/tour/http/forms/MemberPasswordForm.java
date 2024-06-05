package datalab.reinfect.tour.http.forms;

import datalab.reinfect.tour.http.validators.annotations.FieldMatchCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldMatchCheck(
	firstField = "password",
	secondField = "confirm",
	message = "비밀번호가 일치하지 않습니다."
)
public class MemberPasswordForm {
	
	@NotBlank(message = "현재 비밀번호를 입력해 주십시오.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다.")
	@Size(min = 6, max = 30, message = "6~30자로 입력해 주십시오.")
	private String now;
	
	@NotBlank(message = "비밀번호를 입력해 주십시오.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다.")
	@Size(min = 6, max = 30, message = "6~30자로 입력해 주십시오.")
	private String password;
	
	@NotBlank(message = "비밀번호를 다시 한 번 입력해 주십시오.")
	private String confirm;
	
}
