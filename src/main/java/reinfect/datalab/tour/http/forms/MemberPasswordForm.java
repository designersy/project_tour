package reinfect.datalab.tour.http.forms;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import reinfect.datalab.tour.enums.MemberType;
import reinfect.datalab.tour.http.validators.annotations.EqualToValue;
import reinfect.datalab.tour.http.validators.annotations.MemberUniqueCheck;

@Getter
@Setter
@EqualToValue(first = "newPassword", second = "confirm", message = "비밀번호가 서로 일치하지 않습니다.")
public class MemberPasswordForm {

    @NotBlank(message = "기존 비밀번호를 입력해 주십시오.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다. (특수문자 입력 불가)")
    @Size(min = 6, max = 30, message = "6~30자로 입력해 주십시오.")
    private String nowPassword;

    @NotBlank(message = "신규 비밀번호를 입력해 주십시오.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다. (특수문자 입력 불가)")
    @Size(min = 6, max = 30, message = "6~30자로 입력해 주십시오.")
    private String newPassword;

    @NotBlank(message = "신규 비밀번호를 다시 한 번 입력해 주십시오.")
    private String confirm;

}
