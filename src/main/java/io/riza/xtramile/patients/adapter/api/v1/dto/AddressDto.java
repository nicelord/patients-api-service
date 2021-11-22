package io.riza.xtramile.patients.adapter.api.v1.dto;

import io.riza.xtramile.patients.domain.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddressDto {

    private final String street;
    private final String suburb;
    private final String state;
    private final String postcode;

    public static AddressDto toDomain(Address address) {
        return new AddressDto(address.getStreet(),
                address.getSuburb(),
                address.getState(),
                address.getPostcode());
    }
}
