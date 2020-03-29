package org.pietrzakp.proxyservice.infrastructure.dto;

import java.util.Date;

public class RentedBooksDTO {

    private Long id;
    private Long userId;
    private Long bookId;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }
}
