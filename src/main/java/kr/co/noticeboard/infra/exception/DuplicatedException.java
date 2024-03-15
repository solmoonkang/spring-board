package kr.co.noticeboard.infra.exception;

import kr.co.noticeboard.infra.response.ResponseStatus;

public class DuplicatedException extends BusinessLogicException {

    public DuplicatedException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public DuplicatedException(String message) {
        super(message);
    }
}
