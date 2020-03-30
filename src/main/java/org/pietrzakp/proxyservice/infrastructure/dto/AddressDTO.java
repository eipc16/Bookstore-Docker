package org.pietrzakp.proxyservice.infrastructure.dto;

import java.util.Objects;

public class AddressDTO {

    private String country;
    private String city;
    private String street;
    private Integer streetNumber;

    public AddressDTO() {}

    public AddressDTO(String country, String city, String street, Integer streetNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(!(other instanceof AddressDTO))
            return false;
        AddressDTO otherAddress = (AddressDTO) other;
        return Objects.equals(country, otherAddress.country) && Objects.equals(city, otherAddress.city)
                && Objects.equals(street, otherAddress.street) && Objects.equals(streetNumber, otherAddress.streetNumber);
    }


    private AddressDTO(Builder builder) {
        this.country = builder.country;
        this.city = builder.city;
        this.street = builder.street;
        this.streetNumber = builder.streetNumber;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String country;
        private String city;
        private String street;
        private Integer streetNumber;

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AddressDTO build() {
            return new AddressDTO(this);
        }
    }
}
