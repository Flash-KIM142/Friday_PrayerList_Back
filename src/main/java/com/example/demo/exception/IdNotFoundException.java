package com.example.demo.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
    public IdNotFoundException(String msg) {
        super(msg);
    }
}
