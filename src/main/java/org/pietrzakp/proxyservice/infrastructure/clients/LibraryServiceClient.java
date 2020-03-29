package org.pietrzakp.proxyservice.infrastructure.clients;

import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LibraryServiceClient extends BaseClient {

    @Value("${library_service.url}")
    private String BASE_URL;

    public MessageDTO postBookRenting(RentedBooksDTO rentedBooksDTO) {
        return getRest().postForObject(BASE_URL + "/rentals", rentedBooksDTO, MessageDTO.class);
    }
}
