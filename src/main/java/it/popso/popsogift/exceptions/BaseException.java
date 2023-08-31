package it.popso.popsogift.exceptions;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public abstract class BaseException extends RuntimeException {

    private Collection<String> messages;

    public BaseException(String msg){
        super(msg);
    }


    public BaseException(String msg, Exception cause){
        super(msg, cause);
    }


    public BaseException(Collection<String> messages){
        super();
        this.messages= messages;
    }


    public BaseException (Collection<String> messages, Exception cause){
        super(cause);
        this.messages= messages;
    }

    @Override
    public String getMessage(){
        String msg;

        if(this.messages!=null && !this.messages.isEmpty()){
            msg="[";

            for(String message : this.messages){
                msg+=message+",";
            }

            msg= StringUtils.removeEnd(msg, ",")+"]";

        }else msg= super.getMessage();

        return msg;
    }
}
