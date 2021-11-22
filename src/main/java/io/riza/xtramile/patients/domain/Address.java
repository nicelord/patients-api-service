package io.riza.xtramile.patients.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.With;

@ToString
@Getter
@RequiredArgsConstructor
@With
public class Address {
    private final String street;
    private final String suburb;
    private final String state;
    private final String postcode;
}
