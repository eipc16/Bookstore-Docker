package org.pietrzakp.proxyservice.infrastructure.dto;

public class RentedBooksDTO {

    private Long id;
    private Long userId;
    private Long bookId;

    public RentedBooksDTO() {}

    public RentedBooksDTO(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.bookId = builder.bookId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private Long bookId;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withBookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public RentedBooksDTO build() {
            return new RentedBooksDTO(this);
        }
    }
}
