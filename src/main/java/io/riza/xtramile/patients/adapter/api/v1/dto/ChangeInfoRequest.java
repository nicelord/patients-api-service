package io.riza.xtramile.patients.adapter.api.v1.dto;

import io.riza.xtramile.patients.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeInfoRequest {
    private String firstName;
    private String lastName;
    @Past
    private LocalDate dob;
    private Gender gender;
    private String phone;
    private String street;
    private String suburb;
    private String state;
    private String postCode;
}
