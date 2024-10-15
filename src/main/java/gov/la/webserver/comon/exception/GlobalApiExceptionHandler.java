package gov.la.webserver.comon.exception;

import gov.la.webserver.comon.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorResponse> BusinessLogicException(final BusinessLogicException ex) {
        final ErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatus()).body(new ErrorResponse(errorCode));
    }
}