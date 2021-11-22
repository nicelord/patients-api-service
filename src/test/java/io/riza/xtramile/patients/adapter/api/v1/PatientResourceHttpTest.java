package io.riza.xtramile.patients.adapter.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.riza.xtramile.patients.adapter.api.v1.dto.ChangeInfoRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.CreatePatientRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.PageResponse;
import io.riza.xtramile.patients.adapter.api.v1.dto.PatientDto;
import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientResource.class)
class PatientResourceHttpTest {

    @MockBean
    PatientResource patientResource;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findById_positive_tests() throws Exception {

        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));
        when(patientResource.findById(anyLong()))
                .thenReturn(PatientDto.of(patient));

        RequestBuilder accept = MockMvcRequestBuilders.get("/v1/patients/1").accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(accept)
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(PatientDto.of(patient))))
        ;

    }

    @Test
    void findById_negative_tests() throws Exception {

        when(patientResource.findById(anyLong()))
                .thenThrow(EntityNotFoundException.class);

        RequestBuilder accept = MockMvcRequestBuilders.get("/v1/patients/1").accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(accept)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is("data not found")))
        ;

    }

    @Test
    void create_positive_test() throws Exception {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        when(patientResource.create(any(CreatePatientRequest.class))).thenReturn(PatientDto.of(patient));

        CreatePatientRequest createPatientRequest = new CreatePatientRequest();
        createPatientRequest.setFirstName("riza");
        createPatientRequest.setLastName("m");
        createPatientRequest.setDob(LocalDate.parse("2020-01-01"));
        createPatientRequest.setGender(Gender.MALE);
        createPatientRequest.setPhone("123123");
        createPatientRequest.setStreet("a");
        createPatientRequest.setSuburb("b");
        createPatientRequest.setState("c");
        createPatientRequest.setPostCode("d");

        RequestBuilder create = post("/v1/patients")
                .content(objectMapper.writeValueAsString(createPatientRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(create)
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(PatientDto.of(patient))));
    }

    @Test
    void create_negative_test() throws Exception {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        when(patientResource.create(any(CreatePatientRequest.class))).thenReturn(PatientDto.of(patient));

        CreatePatientRequest createPatientRequest = new CreatePatientRequest();
        createPatientRequest.setFirstName("");
        createPatientRequest.setLastName("m");
        createPatientRequest.setDob(LocalDate.parse("2020-01-01"));
        createPatientRequest.setGender(Gender.MALE);
        createPatientRequest.setPhone("123123");
        createPatientRequest.setStreet("a");
        createPatientRequest.setSuburb("b");
        createPatientRequest.setState("c");


        RequestBuilder create = post("/v1/patients")
                .content(objectMapper.writeValueAsString(createPatientRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(create)
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.firstName", is("must not be blank")))
                .andExpect(jsonPath("$.postCode", is("must not be blank")));
    }


    @Test
    void delete() throws Exception {

        doNothing().when(patientResource).delete(2L);
        RequestBuilder delete = MockMvcRequestBuilders.delete("/v1/patients/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(delete)
                .andExpect(status().isOk());

        doThrow(new EntityNotFoundException()).when(patientResource).delete(2L);
        RequestBuilder delete2 = MockMvcRequestBuilders.delete("/v1/patients/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(delete2)
                .andExpect(status().is(404));
    }

    @Test
    void update_positive_test() throws Exception {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        when(patientResource.update(anyLong(), any(ChangeInfoRequest.class))).thenReturn(PatientDto.of(patient));

        ChangeInfoRequest createPatientRequest = new ChangeInfoRequest();
        createPatientRequest.setFirstName("x");
        createPatientRequest.setLastName(null);


        RequestBuilder create = put("/v1/patients/1")
                .content(objectMapper.writeValueAsString(createPatientRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(create)
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(PatientDto.of(patient))));

    }

    @Test
    void update_negative_test() throws Exception {
        Patient patient = new Patient(1L, "Riza", "M", LocalDate.parse("1987-01-01"), Gender.MALE, "+123123123", new Address("Jl. Soedirman", "Nongsa", "Kepulauan Riau", "26464"));

        when(patientResource.update(anyLong(), any(ChangeInfoRequest.class))).thenReturn(PatientDto.of(patient));

        ChangeInfoRequest createPatientRequest = new ChangeInfoRequest();
        createPatientRequest.setFirstName("");
        createPatientRequest.setLastName(" ");
        createPatientRequest.setDob(LocalDate.parse("2020-01-01"));
        createPatientRequest.setGender(Gender.MALE);
        createPatientRequest.setPhone("123123");
        createPatientRequest.setStreet("a");
        createPatientRequest.setSuburb(" ");
        createPatientRequest.setState("c");


        RequestBuilder create = put("/v1/patients/1")
                .content(objectMapper.writeValueAsString(createPatientRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(create)
                .andExpect(status().is(200));

    }

    @Test
    void listByName_positive_test() throws Exception {

        PageResponse<PatientDto> pageResponse = new PageResponse<>();
        pageResponse.setData(Collections.emptyList());
        pageResponse.setTotalPages(10);
        pageResponse.setCurrentPage(1);
        pageResponse.setTotalItems(100);

        when(patientResource.list("riza", 1, 10)).thenReturn(pageResponse);

        RequestBuilder search = get("/v1/patients/searchByName")
                .queryParam("name", "riza")
                .queryParam("page", "1")
                .queryParam("size","10")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(search)
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(pageResponse)));

    }


}