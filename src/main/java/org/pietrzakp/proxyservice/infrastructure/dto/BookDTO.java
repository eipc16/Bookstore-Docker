package org.pietrzakp.proxyservice.infrastructure.dto;

public class BookDTO {

    private Long id;
    private String bookName;
    private String authorName;

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
