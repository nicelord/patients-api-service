package io.riza.xtramile.patients.adapter.api.v1;

import io.riza.xtramile.patients.adapter.api.service.PatientsApiService;
import io.riza.xtramile.patients.adapter.api.v1.dto.ChangeInfoRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.CreatePatientRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.PatientDto;
import io.riza.xtramile.patients.adaptersutils.PageResult;
import io.riza.xtramile.patients.domain.Patient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(value = "/v1/patients")
public class PatientResource {

    private final PatientsApiService patientsApiService;

    public PatientResource(PatientsApiService patientsApiService) {
        this.patientsApiService = patientsApiService;
    }

    @GetMapping(value = "/{pid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PatientDto findById(@PathVariable Long pid) {
        Patient patient = patientsApiService.findById(pid);
        return PatientDto.of(patient);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PatientDto create(@Valid @RequestBody CreatePatientRequest request) {
        Patient patient = patientsApiService.create(request);
        return PatientDto.of(patient);
    }

    @DeleteMapping(value = "/{pid}")
    public void delete(@PathVariable Long pid) {
        patientsApiService.delete(pid);
    }

    @PutMapping(value = "/{pid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PatientDto update(@PathVariable Long pid, @Valid @RequestBody ChangeInfoRequest request) {
        Patient patient = patientsApiService.changeInfo(pid, request);
        return PatientDto.of(patient);
    }

    @GetMapping(value = "/searchByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResult<PatientDto> list(@RequestParam String name,
                                       @RequestParam(defaultValue = "1") @Min(value = 1, message = "must not be less than 1") int page,
                                       @RequestParam(defaultValue = "10") @Min(value = 10, message = "must not be less than 10") int size) {
        return patientsApiService.search(name, page, size);
    }

    @GetMapping(value = "/searchByPid", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResult<PatientDto> list(@RequestParam(defaultValue = "0") Long pid,
                                       @RequestParam(defaultValue = "1") @Min(value = 1, message = "must not be less than 1") int page,
                                       @RequestParam(defaultValue = "10") @Min(value = 10, message = "must not be less than 10") int size) {
        return patientsApiService.search(pid, page, size);
    }

}
