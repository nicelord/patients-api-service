package io.riza.xtramile.patients.adapter.api.v1;

import io.riza.xtramile.patients.adapter.api.service.PatientsApiService;
import io.riza.xtramile.patients.adapter.api.v1.dto.ChangeInfoRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.CreatePatientRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.PageResponse;
import io.riza.xtramile.patients.adapter.api.v1.dto.PatientDto;
import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;
import io.riza.xtramile.patients.port.in.ChangePatientInfoCmd;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientResourceTest {

    @Mock
    PatientsApiService patientsApiService;

    @InjectMocks
    PatientResource patientResource;

    @Test
    void findById() {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));
        when(patientsApiService.findById(anyLong()))
                .thenReturn(patient);
        patientResource.findById(2L);
        verify(patientsApiService).findById(2L);
    }

    @Test
    void create() {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        when(patientsApiService.create(any(CreatePatientRequest.class))).thenReturn(patient);

        patientResource.create(new CreatePatientRequest("riza", "m", LocalDate.parse("2021-01-01"),
                Gender.MALE,
                "123123",
                "a", "b", "c", "d"));

        ArgumentCaptor<CreatePatientRequest> argument = ArgumentCaptor.forClass(CreatePatientRequest.class);
        verify(patientsApiService).create(argument.capture());

        assertEquals("riza", argument.getValue().getFirstName());
        assertEquals("m", argument.getValue().getLastName());
        assertEquals(LocalDate.parse("2021-01-01"), argument.getValue().getDob());
        assertEquals(Gender.MALE, argument.getValue().getGender());
        assertEquals("123123", argument.getValue().getPhone());
        assertEquals("a", argument.getValue().getStreet());
        assertEquals("b", argument.getValue().getSuburb());
        assertEquals("c", argument.getValue().getState());
        assertEquals("d", argument.getValue().getPostCode());
    }

    @Test
    void delete() {

        doNothing().when(patientsApiService).delete(2L);
        patientResource.delete(2L);
        verify(patientsApiService).delete(2L);
    }

    @Test
    void update() {

        Patient patient = new Patient(2L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        ChangeInfoRequest changeInfoRequest = new ChangeInfoRequest();
        changeInfoRequest.setLastName("B");

        when(patientsApiService.changeInfo(2L, changeInfoRequest)).thenReturn(patient);

        patientResource.update(2L, changeInfoRequest);

        verify(patientsApiService).changeInfo(2L,changeInfoRequest);

    }

    @Test
    void listByName() {
        PageResponse<PatientDto> pageResponse = new PageResponse<>();
        pageResponse.setData(Collections.emptyList());
        pageResponse.setTotalPages(10);
        pageResponse.setCurrentPage(1);
        pageResponse.setTotalItems(100);
        when(patientsApiService.search("riza", 1, 10))
                .thenReturn(pageResponse);
        patientResource.list("riza", 1, 10);
        verify(patientsApiService).search("riza", 1, 10);

    }

    @Test
    void listById() {
        PageResponse<PatientDto> pageResponse = new PageResponse<>();
        pageResponse.setData(Collections.emptyList());
        pageResponse.setTotalPages(10);
        pageResponse.setCurrentPage(1);
        pageResponse.setTotalItems(100);
        when(patientsApiService.search(2L, 1, 10))
                .thenReturn(pageResponse);
        patientResource.list(2L, 1, 10);
        verify(patientsApiService).search(2L, 1, 10);
    }
}