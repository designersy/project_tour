package reinfect.datalab.tour.http.forms;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import reinfect.datalab.tour.enums.MemberType;
import reinfect.datalab.tour.http.validators.annotations.EqualToValue;
import reinfect.datalab.tour.http.validators.annotations.MemberUniqueCheck;

@Getter
@Setter
@EqualToValue(first = "password", second = "confirm", message = "{validator.password.confirm}")
public class MemberRegisterForm {

    @NotBlank(message = "{validator.username.not_blank}")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validator.username.pattern}")
    @Size(min = 6, max = 30, message = "{validator.username.size}")
    @MemberUniqueCheck(dataType = MemberType.USERNAME, message = "{validator.username.unique}")
    private String username;

    @NotBlank(message = "{validator.password.not_blank}")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validator.password.not_blank}")
    @Size(min = 6, max = 30, message = "{validator.password.size}")
    private String password;

    @NotBlank(message = "{validator.confirm.not_blank}")
    private String confirm;

    @NotBlank(message = "{validator.nickname.not_blank}")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "{validator.nickname.pattern}")
    @Size(min = 2, max = 10, message = "{validator.nickname.size}")
    @MemberUniqueCheck(dataType = MemberType.NICKNAME, message = "{validator.nickname.unique}")
    private String nickname;

    @NotBlank(message = "{validator.email.not_blank}")
    @Email(message = "{validator.email.email}")
    @MemberUniqueCheck(dataType = MemberType.EMAIL, message = "{validator.email.unique}")
    private String email;

    @AssertTrue(message = "{validator.terms.agree}")
    private boolean terms;

    @AssertTrue(message = "{validator.privacy.agree}")
    private boolean privacy;

}
