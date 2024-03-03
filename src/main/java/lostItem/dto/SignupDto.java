package lostItem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    @NotBlank(message = "이름은 필수값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수값입니다.")
    private String phoneNumber;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;

}
