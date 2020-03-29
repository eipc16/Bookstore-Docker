package org.pietrzakp.proxyservice.infrastructure.handlers;

import org.pietrzakp.proxyservice.infrastructure.exceptions.ExternalServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ExternalClientExceptionHandler implements ResponseErrorHandler {

    private final String serviceName;

    public ExternalClientExceptionHandler(String serviceName) {
        this.serviceName = serviceName;
    }


    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus status = clientHttpResponse.getStatusCode();
        return status.series() == HttpStatus.Series.CLIENT_ERROR || status.series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus status = clientHttpResponse.getStatusCode();
        if (status.series() == HttpStatus.Series.CLIENT_ERROR || status.series() == HttpStatus.Series.SERVER_ERROR) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()))) {
                String errorMessage = reader.lines().collect(Collectors.joining(""));
                throw new ExternalServiceException(serviceName, status, errorMessage);
            }
        }
        throw new RuntimeException(clientHttpResponse.toString());
    }
}
