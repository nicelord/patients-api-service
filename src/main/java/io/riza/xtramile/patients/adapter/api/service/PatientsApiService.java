package io.riza.xtramile.patients.adapter.api.service;

import io.riza.xtramile.patients.adapter.api.v1.dto.ChangeInfoRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.CreatePatientRequest;
import io.riza.xtramile.patients.adapter.api.v1.dto.PageResponse;
import io.riza.xtramile.patients.adapter.api.v1.dto.PatientDto;
import io.riza.xtramile.patients.adaptersutils.PageResult;
import io.riza.xtramile.patients.adaptersutils.PatientPagerQuery;
import io.riza.xtramile.patients.domain.Patient;
import io.riza.xtramile.patients.port.in.ChangePatientInfoCmd;
import io.riza.xtramile.patients.port.in.CreatePatientCmd;
import io.riza.xtramile.patients.port.in.PatientCommand;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PatientsApiService {

    private final PatientCommand patientCommand;
    private final PatientPagerQuery patientPagerQuery;

    public PatientsApiService(PatientCommand patientCommand, PatientPagerQuery patientPagerQuery) {
        this.patientCommand = patientCommand;
        this.patientPagerQuery = patientPagerQuery;
    }

    public Patient create(CreatePatientRequest request) {
        CreatePatientCmd createPatientCmd = new CreatePatientCmd(
                request.getFirstName(),
                request.getLastName(),
                request.getDob(),
                request.getGender(),
                request.getPhone(),
                request.getStreet(),
                request.getSuburb(),
                request.getState(),
                request.getPostCode());
        return patientCommand.create(createPatientCmd);
    }

    public void delete(Long pid) {
        patientCommand.delete(pid);
    }

    public Patient changeInfo(Long pid, ChangeInfoRequest request) {
        return patientCommand.changeInfo(pid, new ChangePatientInfoCmd(request.getFirstName(),
                request.getLastName(), request.getDob(), request.getGender(), request.getPhone(),
                request.getStreet(), request.getSuburb(), request.getState(), request.getPostCode()));

    }

    public Patient findById(Long pid) {
        return patientCommand.findById(pid);
    }

    public PageResponse<PatientDto> search(String name, int page, int size) {
        PageResult<Patient> patientPageResult = patientPagerQuery.searchByName(name, page - 1, size);
        return buildPage(patientPageResult);
    }

    public PageResponse<PatientDto> search(Long pid, int page, int size) {
        PageResult<Patient> patientPageResult = patientPagerQuery.searchByPid(pid, page - 1, size);
        return buildPage(patientPageResult);
    }

    public PageResult<PatientDto> all(int page, int size) {
        PageResult<Patient> all = patientPagerQuery.all(page-1, size);
        return buildPage(all);
    }


    private PageResponse<PatientDto> buildPage(PageResult<Patient> patientPageResult) {
        PageResponse<PatientDto> pageResponse = new PageResponse<>();
        pageResponse.setData(patientPageResult.getData().stream().map(PatientDto::of).collect(Collectors.toList()));
        pageResponse.setCurrentPage(patientPageResult.getCurrentPage());
        pageResponse.setTotalItems(patientPageResult.getTotalItems());
        pageResponse.setTotalPages(patientPageResult.getTotalPages());
        return pageResponse;
    }


}
