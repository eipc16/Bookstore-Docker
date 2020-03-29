package org.pietrzakp.proxyservice.infrastructure.clients;

import org.pietrzakp.proxyservice.infrastructure.dto.BookDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BookServiceClient extends BaseClient {

    @Value("${book_service.url}")
    private String BASE_URL;

    public MessageDTO putBook(BookDTO bookDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/**");
        HttpEntity<BookDTO> requestEntity = new HttpEntity<>(bookDTO, headers);
        return getRest().exchange(BASE_URL + "/books/" + bookDTO.getId(), HttpMethod.PUT, requestEntity, MessageDTO.class, Collections.emptyMap()).getBody();
    }
}