package org.pietrzakp.proxyservice.configuration;

import org.pietrzakp.proxyservice.infrastructure.exceptions.ExternalServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionAdvisor extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvisor.class);

    @ExceptionHandler(value = ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handleExternalServiceException(ExternalServiceException ex, HttpServletRequest request) {
        LOGGER.error("An error happened while calling '{}', Message: '{}'", ex.getServiceName(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorResponse(ex, request.getRequestURI()));
    }
}
