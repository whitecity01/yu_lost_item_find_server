package lostItem.domain.user.service;

import lostItem.businessProcess.auth.dto.SigninDto;
import lostItem.businessProcess.auth.dto.SignupDto;

public interface UserService {
    public void checkExits(SignupDto user);
    public void register(SignupDto user);
    public void login(SigninDto signinDto);


}
