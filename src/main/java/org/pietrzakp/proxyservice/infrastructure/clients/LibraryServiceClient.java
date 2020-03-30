package org.pietrzakp.proxyservice.infrastructure.clients;

import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.pietrzakp.proxyservice.infrastructure.handlers.ExternalClientExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class LibraryServiceClient extends BaseClient {

    @Value("${library_service.url}")
    private String baseUrl;

    private static final String SERVICE_NAME = "library-service";

    public LibraryServiceClient() {
        this(new RestTemplateBuilder());
    }

    public LibraryServiceClient(String serviceUrl) {
        this(new RestTemplateBuilder());
        this.baseUrl = serviceUrl;
    }

    public LibraryServiceClient(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder, new ExternalClientExceptionHandler(SERVICE_NAME));
    }

    public MessageDTO postBookRenting(RentedBooksDTO rentedBooksDTO) {
        return getRest().postForObject(baseUrl + "/rentals", rentedBooksDTO, MessageDTO.class);
    }
}
