package io.riza.xtramile.patients.service;

import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Patient;
import io.riza.xtramile.patients.port.in.ChangePatientInfoCmd;
import io.riza.xtramile.patients.port.in.CreatePatientCmd;
import io.riza.xtramile.patients.port.in.PatientCommand;
import io.riza.xtramile.patients.port.out.PatientStore;

public class PatientCommandImpl implements PatientCommand {

    private final PatientStore patientStore;

    public PatientCommandImpl(PatientStore patientStore) {
        this.patientStore = patientStore;
    }

    @Override
    public Patient changeInfo(Long pid, ChangePatientInfoCmd cmd) {
        Patient patient = patientStore.get(pid);
        patient.changeInfo(cmd.getFirstName(),
                cmd.getLastName(),
                cmd.getDob(),
                cmd.getGender(),
                cmd.getPhone(),
                new Address(cmd.getStreet(),
                        cmd.getSuburb(),
                        cmd.getState(),
                        cmd.getPostcode()));

        patientStore.update(pid,
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDob(),
                patient.getGender(),
                patient.getPhone(),
                patient.getAddress());

        return patientStore.get(pid);
    }

    @Override
    public Patient create(CreatePatientCmd cmd) {
        Patient p = new Patient(null, cmd.getFirstName(), cmd.getLastName(), cmd.getDob(), cmd.getGender(), cmd.getPhone(),
                new Address(cmd.getStreet(), cmd.getSuburb(), cmd.getState(), cmd.getPostcode()));
        return patientStore.save(p);
    }

    @Override
    public Patient findById(Long id) {
        return patientStore.get(id);
    }

    @Override
    public void delete(Long pid) {
        patientStore.delete(pid);
    }


}
