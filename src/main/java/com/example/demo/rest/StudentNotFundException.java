package com.example.demo.rest;

public class StudentNotFundException extends RuntimeException{

    public StudentNotFundException(String message) {
        super(message);
    }

    public StudentNotFundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFundException(Throwable cause) {
        super(cause);
    }
}
