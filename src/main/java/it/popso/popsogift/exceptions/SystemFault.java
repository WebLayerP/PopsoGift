package it.popso.popsogift.exceptions;

import java.util.Collection;

public class SystemFault extends BaseException {
    public SystemFault(String msg) {
        super(msg);
    }

    public SystemFault(String msg, Exception cause) {
        super(msg, cause);
    }

    public SystemFault(Collection<String> messages) {
        super(messages);
    }

    public SystemFault(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}