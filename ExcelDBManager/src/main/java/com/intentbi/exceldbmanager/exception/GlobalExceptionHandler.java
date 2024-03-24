package com.intentbi.exceldbmanager.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NoHandlerFoundException and returns a ResponseEntity with an ErrorDetail object.
     * @param ex The NoHandlerFoundException object.
     * @param wr The WebRequest object.
     * @return ResponseEntity with an ErrorDetail object and HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetail> noHandler(NoHandlerFoundException ex, WebRequest wr) {
        return new ResponseEntity<>(new ErrorDetail(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with an ErrorDetail object.
     * @param ex The MethodArgumentNotValidException object.
     * @param wr The WebRequest object.
     * @return ResponseEntity with an ErrorDetail object and HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail> noHandler(MethodArgumentNotValidException ex, WebRequest wr) {
        return new ResponseEntity<>(new ErrorDetail(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles general Exception and returns a ResponseEntity with an ErrorDetail object.
     * @param ex The Exception object.
     * @param wr The WebRequest object.
     * @return ResponseEntity with an ErrorDetail object and HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> parentException(Exception ex, WebRequest wr) {
        return new ResponseEntity<>(new ErrorDetail(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IntentBiException and returns a ResponseEntity with an ErrorDetail object.
     * @param ex The IntentBiException object.
     * @param wr The WebRequest object.
     * @return ResponseEntity with an ErrorDetail object and HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(IntentBiException.class)
    public ResponseEntity<ErrorDetail> payWithStripeException(IntentBiException ex, WebRequest wr) {
        return new ResponseEntity<>(new ErrorDetail(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
