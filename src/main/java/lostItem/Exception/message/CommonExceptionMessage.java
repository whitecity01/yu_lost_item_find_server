package lostItem.Exception.message;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommonExceptionMessage {
    public static final String UserAlreadyExists = "이미 존재하는 사용자의 이메일 입니다.";
    //public static final String BindingErrorMessage = "필드값이 유효하지 않습니다. 올바르 필드값을 입력해주세요.";
    public static final String DuplicatedUniqueKey = "이미 등록된 정보입니다. 다른 정보를 입력해주세요.";
    public static final String  UsernameNotFoundException = "입력하신 이메일의 계정이 존재하지 않습니다.";
    public static final String ACCOUNT_PASSWORD_NOT_MATCH = "입력하신 Password가 일치하지 않습니다.";
}
