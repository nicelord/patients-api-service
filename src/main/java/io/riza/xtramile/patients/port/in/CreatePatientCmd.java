package io.riza.xtramile.patients.port.in;

import io.riza.xtramile.patients.domain.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class CreatePatientCmd {
    private final String firstName;
    private final String lastName;
    private final LocalDate dob;
    private final Gender gender;
    private final String phone;
    private final String street;
    private final String suburb;
    private final String state;
    private final String postcode;
}
