package reinfect.datalab.tour.http.forms;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import reinfect.datalab.tour.enums.MemberType;
import reinfect.datalab.tour.http.validators.annotations.EqualToValue;
import reinfect.datalab.tour.http.validators.annotations.MemberUniqueCheck;

@Getter
@Setter
public class MemberUpdateForm {

    @NotBlank(message = "닉네임(별명)을 입력해 주십시오.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "한글, 영문, 숫자만 입력 가능합니다. (특수문자 입력 불가)")
    @Size(min = 2, max = 10, message = "2~10자로 입력해 주십시오.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해 주십시오.")
    @Email(message = "올바른 이메일을 입력해 주십시오.")
    @MemberUniqueCheck(dataType = MemberType.EMAIL, message = "이미 가입된 회원입니다.")
    private String email;

}
