package br.com.fiap.gs_java.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    record ErrorResponse(String message) {}

    @ExceptionHandler({EmailNotFoundException.class, InvalidPasswordException.class})
    public ResponseEntity<?> handleNotFoundorInvaidException(RuntimeException exception) {
        return ResponseEntity
                .status(404)
                .body(new ErrorResponse(exception.getMessage()));
    }

}