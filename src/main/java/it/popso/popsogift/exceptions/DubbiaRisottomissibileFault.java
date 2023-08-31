package it.popso.popsogift.exceptions;

import java.util.Collection;

public class DubbiaRisottomissibileFault extends BaseException {
    public DubbiaRisottomissibileFault(String msg) {
        super(msg);
    }

    public DubbiaRisottomissibileFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public DubbiaRisottomissibileFault(Collection<String> messages) {
        super(messages);
    }

    public DubbiaRisottomissibileFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}