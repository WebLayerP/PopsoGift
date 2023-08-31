package it.popso.popsogift.exceptions;

import java.util.Collection;

public class ServizioNonDisponibileFault extends BaseException {
    public ServizioNonDisponibileFault(String msg) {
        super(msg);
    }

    public ServizioNonDisponibileFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public ServizioNonDisponibileFault(Collection<String> messages) {
        super(messages);
    }

    public ServizioNonDisponibileFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}