package org.pietrzakp.proxyservice.configuration;

import org.pietrzakp.proxyservice.infrastructure.exceptions.ExternalServiceException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {

    private String exception;
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String service;
    private String localPath;

    ErrorResponse(ExternalServiceException ex, String path) {
        this.exception = ex.getClass().getSimpleName();
        this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        this.status = ex.getStatus().value();
        this.error = ex.getStatus().getReasonPhrase();
        this.message = ex.getMessage();
        this.service = ex.getServiceName();
        this.localPath = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getService() {
        return service;
    }

    public String getLocalPath() {
        return localPath;
    }

    public String getException() {
        return exception;
    }
}
