package it.popso.popsogift.exceptions;

import java.util.Collection;

public class ApplicationFault extends BaseException{


    public ApplicationFault(String msg) {
        super(msg);
    }

    public ApplicationFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public ApplicationFault(Collection<String> messages) {
        super(messages);
    }

    public ApplicationFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}