package io.riza.xtramile.patients.service;

import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;
import io.riza.xtramile.patients.port.in.ChangePatientInfoCmd;
import io.riza.xtramile.patients.port.in.CreatePatientCmd;
import io.riza.xtramile.patients.port.out.PatientStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientCommandImplTest {

    @Mock
    PatientStore patientStore;

    @InjectMocks
    PatientCommandImpl patientCommand;

    @Test
    void changeInfo() {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        when(patientStore.get(anyLong())).thenReturn(patient);
        Patient xx = patientCommand.changeInfo(1L, new ChangePatientInfoCmd("XX", null, null, null, null, null, null, null, null));
        assertEquals("XX", xx.getFirstName());
        verify(patientStore, times(2)).get(1L);
        verify(patientStore, times(1)).update(1L, "XX", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", xx.getAddress());
    }

    @Test
    void create() {
        when(patientStore.save(any(Patient.class))).thenReturn(any(Patient.class));
        patientCommand.create(new CreatePatientCmd("Riza", "Maizarani", LocalDate.parse("2020-01-01"),Gender.MALE,"123123","a","b","c","d"));
        verify(patientStore).save(any(Patient.class));

    }

    @Test
    void findById() {
        when(patientStore.get(anyLong())).thenReturn(any(Patient.class));
        patientCommand.findById(2L);
        verify(patientStore).get(2L);
    }

    @Test
    void delete() {
        doNothing().when(patientStore).delete(anyLong());
        patientCommand.delete(2L);
        verify(patientStore).delete(2L);

    }
}