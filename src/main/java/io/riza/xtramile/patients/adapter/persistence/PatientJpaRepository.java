package io.riza.xtramile.patients.adapter.persistence;

import io.riza.xtramile.patients.adapter.persistence.model.PatientEntity;
import io.riza.xtramile.patients.adaptersutils.PageResult;
import io.riza.xtramile.patients.adaptersutils.PatientPagerQuery;
import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Gender;
import io.riza.xtramile.patients.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Repository
public class PatientJpaRepository implements PatientPagerQuery {

    private final PatientRepository patientRepository;

    public PatientJpaRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient get(Long pid) {
        PatientEntity patientEntity = patientRepository.findById(pid)
                .orElseThrow(EntityNotFoundException::new);
        return EntityMapper.toPatient(patientEntity);
    }

    @Override
    public void update(Long pid,
                       String firstName,
                       String lastName,
                       LocalDate dob,
                       Gender gender,
                       String phone,
                       Address address) {
        PatientEntity patientEntity = patientRepository.findById(pid).orElseThrow(EntityNotFoundException::new);
        patientEntity.setFirstName(firstName);
        patientEntity.setLastName(lastName);
        patientEntity.setDob(dob);
        patientEntity.setGender(gender);
        patientEntity.setPhone(phone);
        patientEntity.setAddress(EntityMapper.toAddressEntity(address));
        patientRepository.save(patientEntity);

    }

    @Override
    public Patient save(Patient patient) {
        PatientEntity save = patientRepository.save(EntityMapper.toEntity(patient));
        return get(save.getPid());
    }

    @Override
    public void delete(Long pid) {
        patientRepository.delete(patientRepository.getById(pid));
    }

    @Override
    public PageResult<Patient> searchByName(String name, int page, int size) {
        Page<PatientEntity> entityPage = patientRepository.findAllByName(name, PageRequest.of(page, size));
        PageResult<Patient> pageResult = new PageResult<>();
        pageResult.setData(entityPage.get().map(EntityMapper::toPatient).collect(Collectors.toList()));
        pageResult.setTotalPages(entityPage.getTotalPages());
        pageResult.setTotalItems(entityPage.getTotalElements());
        pageResult.setCurrentPage(entityPage.getNumber()+1);
        return pageResult;
    }

    @Override
    public PageResult<Patient> searchByPid(Long pid, int page, int size) {
        Page<PatientEntity> entityPage = patientRepository.findAllByPidEquals(pid, PageRequest.of(page, size));
        PageResult<Patient> pageResult = new PageResult<>();
        pageResult.setData(entityPage.get().map(EntityMapper::toPatient).collect(Collectors.toList()));
        pageResult.setTotalPages(entityPage.getTotalPages());
        pageResult.setTotalItems(entityPage.getTotalElements());
        pageResult.setCurrentPage(entityPage.getNumber()+1);
        return pageResult;
    }
}
