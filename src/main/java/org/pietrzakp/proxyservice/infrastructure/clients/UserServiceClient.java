package org.pietrzakp.proxyservice.infrastructure.clients;

import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;
import org.pietrzakp.proxyservice.infrastructure.handlers.ExternalClientExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClient extends BaseClient {

    @Value("${user_service.url}")
    private String baseUrl;

    private static final String SERVICE_NAME = "users-service";

    public UserServiceClient() {
        this(new RestTemplateBuilder());
    }

    public UserServiceClient(String serviceUrl) {
        this(new RestTemplateBuilder());
        this.baseUrl = serviceUrl;
    }

    public UserServiceClient(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder, new ExternalClientExceptionHandler(SERVICE_NAME));
    }

    public UserDTO getUserById(Long id) {
        return getRest().getForObject(baseUrl + "/users/" + id, UserDTO.class);
    }
}
