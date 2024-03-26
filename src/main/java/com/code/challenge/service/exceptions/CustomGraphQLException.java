package com.code.challenge.service.exceptions;

import org.springframework.http.HttpStatus;

public class CustomGraphQLException extends RuntimeException {

    private HttpStatus statusCode;

    public CustomGraphQLException(HttpStatus status, String message) {
        super(message);
        this.statusCode = status;
    }

    public CustomGraphQLException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
