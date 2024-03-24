package com.intentbi.exceldbmanager.exception;

/**
 * Custom exception class for IntentBI related exceptions.
 */
public class IntentBiException extends RuntimeException {

    /**
     * Constructs a new IntentBiException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public IntentBiException(String message) {
        super(message);
    }
}
