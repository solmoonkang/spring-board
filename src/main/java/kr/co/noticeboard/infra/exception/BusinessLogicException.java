package kr.co.noticeboard.infra.exception;

import kr.co.noticeboard.infra.response.ResponseStatus;

public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException(ResponseStatus responseStatus) {
        super(responseStatus.getMessage());
    }

    public BusinessLogicException(String message) {
        super(message);
    }
}
