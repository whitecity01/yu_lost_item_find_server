package lostItem.Exception.collections.business;

import lostItem.Exception.message.CommonExceptionMessage;

public class UsernameNotFoundException extends BusinessException {
    public UsernameNotFoundException() {
        super(CommonExceptionMessage.UsernameNotFoundException);
    }
}
