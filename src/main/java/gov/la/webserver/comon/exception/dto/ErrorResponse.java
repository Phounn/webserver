package gov.la.webserver.comon.exception.dto;

import gov.la.webserver.comon.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final Integer errorCode;
    private final String message;

    public ErrorResponse(ErrorCode errorCode){
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
