package com.NewGen.MiniBank.exception;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String message){
        super(message);
    }
}
