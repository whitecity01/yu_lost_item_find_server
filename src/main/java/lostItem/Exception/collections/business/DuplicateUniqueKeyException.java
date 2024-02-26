package lostItem.Exception.collections.business;

import lostItem.Exception.message.CommonExceptionMessage;

public class DuplicateUniqueKeyException extends BusinessException{
    public DuplicateUniqueKeyException() {
        super(CommonExceptionMessage.DuplicatedUniqueKey);
    }
}