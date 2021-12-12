package com.fayden.smartorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadArgumentException extends RuntimeException {

    public BadArgumentException() {
        super();
    }

    public BadArgumentException(String message) {
        super(message);
    }

    public BadArgumentException(Throwable e) {
        super(e);
    }
}