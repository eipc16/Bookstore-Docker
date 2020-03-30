package org.pietrzakp.proxyservice.infrastructure.dto;

public class BookDTO {

    private Long id;
    private String bookName;
    private String authorName;

    public BookDTO() {}

    private BookDTO(Builder builder) {
        this.id = builder.id;
        this.bookName = builder.bookName;
        this.authorName = builder.authorName;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String bookName;
        private String authorName;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withBookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder withAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public BookDTO build() {
            return new BookDTO(this);
        }
    }
}
