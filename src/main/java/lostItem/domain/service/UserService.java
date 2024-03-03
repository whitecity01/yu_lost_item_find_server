package lostItem.domain.service;

import lostItem.dto.SigninDto;
import lostItem.dto.SignupDto;

public interface UserService {
    public void checkExits(SignupDto user);
    public void register(SignupDto user);
    public void login(SigninDto signinDto);


}
