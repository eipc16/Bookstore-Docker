package org.pietrzakp.proxyservice.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends RuntimeException {

    private String serviceName;
    private HttpStatus status;
    private String message;

    public ExternalServiceException(String serviceName, HttpStatus status, String message) {
        super();
        this.serviceName = serviceName;
        this.status = status;
        this.message = message;
    }

    public String getServiceName() {
        return serviceName;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
