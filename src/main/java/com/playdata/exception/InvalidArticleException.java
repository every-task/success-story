package com.playdata.exception;

public class InvalidArticleException extends RuntimeException{

    public InvalidArticleException(String message) {
        super(message);
    }
}
