package com.sehati.exception;

public class EntityExistException extends RuntimeException{
    public EntityExistException() {
        super("Data is already exist");
    }

    public EntityExistException(String msg){
        super(msg);
    }
}
