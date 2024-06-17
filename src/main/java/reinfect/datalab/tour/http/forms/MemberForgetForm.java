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
public class MemberForgetForm {

    @NotBlank(message = "{validator.nickname.not_blank}")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "{validator.nickname.pattern}")
    @Size(min = 2, max = 10, message = "{validator.nickname.size}")
    private String nickname;

    @NotBlank(message = "{validator.email.not_blank}")
    @Email(message = "{validator.email.email}")
    private String email;

}
