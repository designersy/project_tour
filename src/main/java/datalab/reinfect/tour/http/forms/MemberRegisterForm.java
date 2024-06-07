package datalab.reinfect.tour.http.forms;

import datalab.reinfect.tour.enums.UniqueType;
import datalab.reinfect.tour.http.validators.annotations.FieldMatchCheck;
import datalab.reinfect.tour.http.validators.annotations.MemberUniqueCheck;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldMatchCheck(
	firstField = "password",
	secondField = "confirm",
	message = "비밀번호가 일치하지 않습니다."
)
public class MemberRegisterForm {

	@NotBlank(message = "아이디를 입력해 주십시오.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다.")
	@Size(min = 4, max = 30, message = "4~30자로 입력해 주십시오.")
	@MemberUniqueCheck(type = UniqueType.USERNAME)
	private String username;
	
	@NotBlank(message = "비밀번호를 입력해 주십시오.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다.")
	@Size(min = 6, max = 30, message = "6~30자로 입력해 주십시오.")
	private String password;
	
	@NotBlank(message = "비밀번호를 다시 한 번 입력해 주십시오.")
	private String confirm;
	
	@NotBlank(message = "별명을 입력해 주십시오.")
	@Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "한글, 영문, 숫자만 입력 가능합니다.")
	@Size(min = 2, max = 10, message = "2~10자로 입력해 주십시오.")
	@MemberUniqueCheck(type = UniqueType.NICKNAME)
	private String nickname;
	
	@NotBlank(message = "이메일을 입력해 주십시오.")
	@Email(message = "올바른 이메일을 입력해 주십시오.")
	@MemberUniqueCheck(type = UniqueType.EMAIL)
	private String email;
	
	private String national;

	@AssertTrue(message = "이용약관에 동의해 주셔야 가입하실 수 있습니다.")
	private boolean terms;

	@AssertTrue(message = "개인정보 처리방침에 동의해 주셔야 가입하실 수 있습니다.")
	private boolean privacy;
	
}
