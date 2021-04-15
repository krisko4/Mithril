package com.website.demo.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer streetNumber;
    private Integer flat_number;
    private String postCode;

    public static AddressDto from(Address address) {
        AddressDto dto = new AddressDto();

        dto.id = address.getId();
        dto.country = address.getCountry();
        dto.city = address.getCity();
        dto.street = address.getStreet();
        dto.flat_number = address.getFlat_number();
        dto.postCode = address.getPostCode();

        return dto;
    }
}
