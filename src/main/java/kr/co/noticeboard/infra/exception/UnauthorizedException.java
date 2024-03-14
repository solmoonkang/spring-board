package kr.co.noticeboard.infra.exception;

import kr.co.noticeboard.infra.response.ResponseStatus;

public class UnauthorizedException extends BusinessLogicException {

    public UnauthorizedException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
