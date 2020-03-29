package org.pietrzakp.proxyservice.infrastructure.dto;

public class AddressDTO {

    private String country;
    private String city;
    private String street;
    private Integer streetNumber;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }
}
