package io.riza.xtramile.patients.port.out;

import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;

import java.time.LocalDate;

public interface PatientStore {

    Patient get(Long pid);

    void update(Long pid,
                String firstName,
                String lastName,
                LocalDate dob,
                Gender gender,
                String phone,
                Address address);

    Patient save(Patient patient);

    void delete(Long pid);

}
