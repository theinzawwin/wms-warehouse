package com.pearl.warehouse.exceptions;

public class NameDuplicateException  extends RuntimeException{
    public NameDuplicateException(String message) {
        super(message);
    }
}
