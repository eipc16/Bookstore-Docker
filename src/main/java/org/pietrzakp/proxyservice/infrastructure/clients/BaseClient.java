package org.pietrzakp.proxyservice.infrastructure.clients;

import org.springframework.web.client.RestTemplate;

public abstract class BaseClient {

    private RestTemplate rest;

    public BaseClient() {
        rest = new RestTemplate();
    }

    public RestTemplate getRest() {
        return rest;
    }
}
