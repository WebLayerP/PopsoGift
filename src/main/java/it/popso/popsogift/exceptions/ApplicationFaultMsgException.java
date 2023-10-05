package it.popso.popsogift.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid Input")
public class ApplicationFaultMsgException extends RuntimeException{


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ApplicationFaultMsgException(String message){
        super(message);
    }

}
