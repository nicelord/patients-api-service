package io.riza.xtramile.patients.adapter.api.v1.dto;

import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PatientDto {
    private final Long pid;
    private final String firstName;
    private final String lastName;
    private final LocalDate dob;
    private final Gender gender;
    private final String phone;
    private final AddressDto address;

    public static PatientDto of(Patient patient) {
        return new PatientDto(patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDob(),
                patient.getGender(),
                patient.getPhone(),
                AddressDto.toDomain(patient.getAddress()));
    }
}
