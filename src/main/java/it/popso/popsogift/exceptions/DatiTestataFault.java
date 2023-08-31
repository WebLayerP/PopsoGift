package it.popso.popsogift.exceptions;

import java.util.Collection;

public class DatiTestataFault extends BaseException {
    public DatiTestataFault(String msg) {
        super(msg);
    }

    public DatiTestataFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public DatiTestataFault(Collection<String> messages) {
        super(messages);
    }

    public DatiTestataFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}