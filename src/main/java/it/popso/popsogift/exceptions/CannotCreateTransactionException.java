package it.popso.popsogift.exceptions;

public class CannotCreateTransactionException extends RuntimeException{


    public CannotCreateTransactionException(String message){
        super(message);
    }

}