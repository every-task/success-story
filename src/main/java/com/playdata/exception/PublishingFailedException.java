package com.playdata.exception;

public class PublishingFailedException extends RuntimeException{

    public PublishingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
