package gov.la.webserver.user.service.exception;

import gov.la.webserver.comon.exception.BusinessLogicException;
import gov.la.webserver.comon.exception.ErrorCode;

public class UserNotFoundException extends BusinessLogicException {
    public UserNotFoundException() {
        super(ErrorCode.NOT_FOUND_USER);
    }
}
