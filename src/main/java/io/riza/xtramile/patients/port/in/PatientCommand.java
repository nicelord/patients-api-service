package io.riza.xtramile.patients.port.in;

import io.riza.xtramile.patients.domain.Patient;

public interface PatientCommand {
    Patient changeInfo(Long pid, ChangePatientInfoCmd cmd);
    Patient create(CreatePatientCmd cmd);
    Patient findById(Long id);
    void delete(Long pid);
}
