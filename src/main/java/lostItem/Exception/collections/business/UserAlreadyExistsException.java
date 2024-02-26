package lostItem.Exception.collections.business;

import lostItem.Exception.message.CommonExceptionMessage;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException() {
        super(CommonExceptionMessage.UserAlreadyExists);
    }
}