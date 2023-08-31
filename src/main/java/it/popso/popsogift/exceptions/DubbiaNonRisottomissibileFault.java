package it.popso.popsogift.exceptions;

import java.util.Collection;

public class DubbiaNonRisottomissibileFault extends BaseException {
    public DubbiaNonRisottomissibileFault(String msg) {
        super(msg);
    }

    public DubbiaNonRisottomissibileFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public DubbiaNonRisottomissibileFault(Collection<String> messages) {
        super(messages);
    }

    public DubbiaNonRisottomissibileFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}