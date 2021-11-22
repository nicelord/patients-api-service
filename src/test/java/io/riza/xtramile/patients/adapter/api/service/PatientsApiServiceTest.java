package io.riza.xtramile.patients.adapter.api.service;

import io.riza.xtramile.patients.adapter.api.v1.dto.ChangeInfoRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.CreatePatientRequest;
import io.riza.xtramile.patients.adaptersutils.PageResult;
import io.riza.xtramile.patients.adaptersutils.PatientPagerQuery;
import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;
import io.riza.xtramile.patients.port.in.ChangePatientInfoCmd;
import io.riza.xtramile.patients.port.in.CreatePatientCmd;
import io.riza.xtramile.patients.port.in.PatientCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientsApiServiceTest {


    @Mock
    PatientCommand patientCommand;

    @Mock
    PatientPagerQuery patientPagerQuery;

    @InjectMocks
    PatientsApiService patientsApiService;

    @Test
    void create() {
        CreatePatientRequest createPatientRequest = new CreatePatientRequest("riza",
                "maizarani",
                LocalDate.parse("2021-01-01"),
                Gender.MALE,
                "123123",
                "a",
                "b",
                "c",
                "d");
        when(patientCommand.create(any(CreatePatientCmd.class))).thenReturn(any(Patient.class));
        patientsApiService.create(createPatientRequest);

        ArgumentCaptor<CreatePatientCmd> argument = ArgumentCaptor.forClass(CreatePatientCmd.class);
        verify(patientCommand).create(argument.capture());
        assertEquals("riza", argument.getValue().getFirstName());
        assertEquals("maizarani", argument.getValue().getLastName());
        assertEquals(LocalDate.parse("2021-01-01"), argument.getValue().getDob());
        assertEquals(Gender.MALE, argument.getValue().getGender());
        assertEquals("123123", argument.getValue().getPhone());
        assertEquals("a", argument.getValue().getStreet());
        assertEquals("b", argument.getValue().getSuburb());
        assertEquals("c", argument.getValue().getState());
        assertEquals("d", argument.getValue().getPostcode());
    }

    @Test
    void delete() {

        doNothing().when(patientCommand).delete(anyLong());
        patientsApiService.delete(2L);
        verify(patientCommand).delete(2L);
    }

    @Test
    void changeInfo() {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        when(patientCommand.changeInfo(anyLong(), any(ChangePatientInfoCmd.class)))
                .thenReturn(patient);

        patientsApiService.changeInfo(2L, new ChangeInfoRequest("riza",
                null,
                null,
                null,
                null,
                null,
                null,
                "xyz",
                null));

        ArgumentCaptor<ChangePatientInfoCmd> argument = ArgumentCaptor.forClass(ChangePatientInfoCmd.class);
        verify(patientCommand).changeInfo(anyLong(), argument.capture());

        assertEquals("riza", argument.getValue().getFirstName());
        assertNull(argument.getValue().getLastName());
        assertNull(argument.getValue().getDob());
        assertNull(argument.getValue().getGender());
        assertNull(argument.getValue().getPhone());
        assertNull(argument.getValue().getStreet());
        assertNull(argument.getValue().getSuburb());
        assertEquals("xyz", argument.getValue().getState());
        assertNull(argument.getValue().getPostcode());
    }

    @Test
    void findById() {
        patientsApiService.findById(2L);
        verify(patientCommand).findById(2L);
    }

    @Test
    void searchByName() {
        PageResult<Patient> patientPageResult = new PageResult<>();
        patientPageResult.setData(Collections.emptyList());
        patientPageResult.setCurrentPage(1);
        patientPageResult.setTotalItems(100);
        patientPageResult.setTotalPages(10);

        when(patientPagerQuery.searchByName(anyString(), anyInt(), anyInt()))
                .thenReturn(patientPageResult);
        patientsApiService.search("riza", 1, 10);
        verify(patientPagerQuery).searchByName("riza", 0, 10);
    }

    @Test
    void searchByPid() {
        PageResult<Patient> patientPageResult = new PageResult<>();
        patientPageResult.setData(Collections.emptyList());
        patientPageResult.setCurrentPage(1);
        patientPageResult.setTotalItems(100);
        patientPageResult.setTotalPages(10);

        when(patientPagerQuery.searchByPid(anyLong(), anyInt(), anyInt()))
                .thenReturn(patientPageResult);
        patientsApiService.search(1L, 1, 10);
        verify(patientPagerQuery).searchByPid(1L, 0, 10);
    }
}