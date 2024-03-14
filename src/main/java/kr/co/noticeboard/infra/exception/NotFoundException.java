package kr.co.noticeboard.infra.exception;

import kr.co.noticeboard.infra.response.ResponseStatus;

public class NotFoundException extends BusinessLogicException {

    public NotFoundException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
