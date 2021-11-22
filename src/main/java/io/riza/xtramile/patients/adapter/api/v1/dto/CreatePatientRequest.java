package io.riza.xtramile.patients.adapter.api.v1.dto;

import io.riza.xtramile.patients.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Past
    private LocalDate dob;
    @NotNull
    private Gender gender;
    @NotBlank
    private String phone;
    @NotBlank
    private String street;
    @NotBlank
    private String suburb;
    @NotBlank
    private String state;
    @NotBlank
    private String postCode;
}
