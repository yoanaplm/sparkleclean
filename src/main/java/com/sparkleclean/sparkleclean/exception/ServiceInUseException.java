package com.sparkleclean.sparkleclean.exception;

public class ServiceInUseException extends RuntimeException {
    public ServiceInUseException(String message) {
        super(message);
    }
}
