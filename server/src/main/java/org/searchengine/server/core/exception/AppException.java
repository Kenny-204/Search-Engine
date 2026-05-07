package org.searchengine.server.core.exception;

import org.springframework.http.HttpStatus;


public class AppException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String status;


    public AppException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.status = statusCode.is4xxClientError() ? "fail" : "Error";
    }

    public static class ErrorResponse {
        public String status;
        public String message;

        public ErrorResponse(String status, String message) {
            this.status = status;
            this.message = message;

        }
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }


}
