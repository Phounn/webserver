package gov.la.webserver.comon.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_USER(100, "not found user",HttpStatus.NOT_FOUND);

    private final  Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
