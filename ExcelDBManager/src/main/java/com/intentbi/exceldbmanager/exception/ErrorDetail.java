package com.intentbi.exceldbmanager.exception;

import java.time.LocalDateTime;

/**
 * Represents details of an error, including message, description, and timestamp.
 */
public record ErrorDetail(String message, String description, LocalDateTime timeStamp) {
}
