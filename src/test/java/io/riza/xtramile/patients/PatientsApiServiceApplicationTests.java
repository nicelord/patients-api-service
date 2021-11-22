package io.riza.xtramile.patients;

import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PatientsApiServiceApplicationTests {

    @Test
    void changeInfo() {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));
        patient.changeInfo(null, "Maizarani", null, null, "+62111111111", null);

        assertEquals("Riza", patient.getFirstName());
        assertEquals("Maizarani", patient.getLastName());
        assertEquals(Gender.MALE, patient.getGender());
        assertEquals(LocalDate.parse("1987-01-01"), patient.getDob());
        assertEquals("+62111111111", patient.getPhone());
        assertEquals(patient.getAddress().getStreet(), "Jl. Soedirman");
        assertEquals(patient.getAddress().getSuburb(), "Nongsa");
        assertEquals(patient.getAddress().getState(), "Kepulauan Riau");
        assertEquals(patient.getAddress().getPostcode(), "26464");

    }
}
