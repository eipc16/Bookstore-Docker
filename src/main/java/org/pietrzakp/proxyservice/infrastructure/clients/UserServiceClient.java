package org.pietrzakp.proxyservice.infrastructure.clients;

import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClient extends BaseClient {

    @Value("${user_service.url}")
    private String BASE_URL;

    public UserDTO getUserById(Long id) {
        return getRest().getForObject(BASE_URL + "/users/" + id, UserDTO.class);
    }
}
