package com.epam.multithreading.exception;

public class SingletoneException extends RuntimeException {
    public SingletoneException() {
    }

    public SingletoneException(String message) {
        super(message);
    }

    public SingletoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public SingletoneException(Throwable cause) {
        super(cause);
    }
}
