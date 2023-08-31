package it.popso.popsogift.exceptions;

import java.util.Collection;

public class DubbiaRisottomissibileConInterventoManualeFault extends BaseException {
    public DubbiaRisottomissibileConInterventoManualeFault(String msg) {
        super(msg);
    }

    public DubbiaRisottomissibileConInterventoManualeFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public DubbiaRisottomissibileConInterventoManualeFault(Collection<String> messages) {
        super(messages);
    }

    public DubbiaRisottomissibileConInterventoManualeFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}