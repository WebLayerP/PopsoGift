package it.popso.popsogift.exceptions;

import java.util.Collection;

public class InputFault extends BaseException {
    public InputFault(String msg) {
        super(msg);
    }

    public InputFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public InputFault(Collection<String> messages) {
        super(messages);
    }

    public InputFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}