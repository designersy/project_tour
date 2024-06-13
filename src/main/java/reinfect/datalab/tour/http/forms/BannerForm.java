package reinfect.datalab.tour.http.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class BannerForm {

    @NotBlank(message = "제목을 입력해 주십시오.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "한글, 영문, 숫자만 입력 가능합니다.")
    @Size(min = 1, max = 40, message = "40자 미만으로 입력해 주십시오.")
    private String subject;

    @NotBlank(message = "내용을 입력해 주십시오.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "한글, 영문, 숫자만 입력 가능합니다.")
    @Size(min = 1, max = 200, message = "200자 미만으로 입력해 주십시오.")
    private String comment;

    @NotBlank(message = "URL을 입력해 주십시오.")
    @URL(message = "올바른 URL을 입력해 주십시오.")
    private String link;

    private String file;

}
