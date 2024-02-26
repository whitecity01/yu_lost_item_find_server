package lostItem.Exception.collections.business;

import lostItem.Exception.message.CommonExceptionMessage;

public class PasswordNotMatchException extends BusinessException {
    public PasswordNotMatchException() {
        super(CommonExceptionMessage.ACCOUNT_PASSWORD_NOT_MATCH);
    }
}