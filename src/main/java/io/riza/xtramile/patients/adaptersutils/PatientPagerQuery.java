package io.riza.xtramile.patients.adaptersutils;

import io.riza.xtramile.patients.domain.Patient;
import io.riza.xtramile.patients.port.out.PatientStore;

public interface PatientPagerQuery extends PatientStore {

    PageResult<Patient> searchByName(String name, int page, int size);

    PageResult<Patient> searchByPid(Long pid, int page, int size);

    PageResult<Patient> all(int page, int size);
}
