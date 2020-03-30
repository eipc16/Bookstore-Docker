package org.pietrzakp.proxyservice.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private AddressDTO address;

    public UserDTO() {

    }

    private UserDTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(!(other instanceof UserDTO))
            return false;
        UserDTO otherUser = (UserDTO) other;
        return Objects.equals(id, otherUser.id) && Objects.equals(firstName, otherUser.firstName)
                && Objects.equals(lastName, otherUser.lastName) && Objects.equals(address, otherUser.address);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private AddressDTO address;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withAddress(AddressDTO address) {
            this.address = address;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
