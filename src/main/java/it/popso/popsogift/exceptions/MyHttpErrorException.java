package it.popso.popsogift.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Forbidden")
public class MyHttpErrorException extends RuntimeException {
    public MyHttpErrorException(String message){
        super(message);
    }
}
