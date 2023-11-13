package com.playdata.exception;

import com.playdata.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(NotCorrectMemberException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleNotCorrectMember(NotCorrectMemberException e) {
        log.error("Not Correct",e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(InvalidArticleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotInvalidArticle(InvalidArticleException e) {
        log.error("Not Invalid Article id",e);
        return new ErrorResponse(e.getMessage());
    }

}
