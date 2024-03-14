package kr.co.noticeboard.infra.exception;

import kr.co.noticeboard.infra.response.ResponseStatus;

public class InvalidRequestException extends BusinessLogicException {

    public InvalidRequestException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
