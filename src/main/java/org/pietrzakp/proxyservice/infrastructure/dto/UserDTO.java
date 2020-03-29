package org.pietrzakp.proxyservice.infrastructure.dto;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private AddressDTO addressDTO;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public Long getId() {
        return id;
    }
}
