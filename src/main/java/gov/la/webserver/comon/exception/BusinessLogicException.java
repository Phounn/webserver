package gov.la.webserver.comon.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {
    final ErrorCode errorCode;

    public BusinessLogicException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

