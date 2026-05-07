package org.searchengine.server.core.middleware;

import org.searchengine.server.core.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppException.ErrorResponse> handleAppException(AppException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(new AppException.ErrorResponse(exception.getStatus(), exception.getMessage()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppException.ErrorResponse> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AppException.ErrorResponse("Error", "Something went wrong"));

    }
}
