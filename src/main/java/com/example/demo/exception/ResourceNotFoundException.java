package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends Exception {
    private static final long serialVerisonUID = 1L;
    public ResourceNotFoundException(String message){
        super(message);
    }
}
