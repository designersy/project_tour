package datalab.reinfect.tour.http.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateForm {
	
	@NotBlank(message = "별명을 입력해 주십시오.")
	@Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "한글, 영문, 숫자만 입력 가능합니다.")
	@Size(min = 2, max = 10, message = "2~10자로 입력해 주십시오.")
	private String nickname;
	
	@NotBlank(message = "이메일을 입력해 주십시오.")
	@Email(message = "올바른 이메일을 입력해 주십시오.")
	private String email;
	
	private String national;
	
}
