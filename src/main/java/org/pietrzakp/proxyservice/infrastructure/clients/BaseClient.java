package org.pietrzakp.proxyservice.infrastructure.clients;

import org.pietrzakp.proxyservice.infrastructure.handlers.ExternalClientExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public abstract class BaseClient {

    private RestTemplate rest;

    public BaseClient(RestTemplateBuilder restTemplateBuilder, ExternalClientExceptionHandler externalClientExceptionHandler) {
        rest = restTemplateBuilder
                .errorHandler(externalClientExceptionHandler)
                .build();
    }

    RestTemplate getRest() {
        return rest;
    }
}
