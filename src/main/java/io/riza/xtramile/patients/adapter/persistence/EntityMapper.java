package io.riza.xtramile.patients.adapter.persistence;

import io.riza.xtramile.patients.adapter.persistence.model.AddressEmbeddable;
import io.riza.xtramile.patients.adapter.persistence.model.PatientEntity;
import io.riza.xtramile.patients.domain.Address;
import io.riza.xtramile.patients.domain.Patient;

public class EntityMapper {

    public static Patient toPatient(PatientEntity patientEntity) {
        return new Patient(patientEntity.getPid(),
                patientEntity.getFirstName(),
                patientEntity.getLastName(),
                patientEntity.getDob(),
                patientEntity.getGender(),
                patientEntity.getPhone(),
                toAddress(patientEntity.getAddress())
        );
    }

    private static Address toAddress(AddressEmbeddable address) {
        return new Address(address.getStreet(),
                address.getSuburb(),
                address.getState(),
                address.getPostcode());
    }

    public static PatientEntity toEntity(Patient patient) {
        return new PatientEntity(patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDob(),
                patient.getGender(),
                patient.getPhone(),
                toAddressEntity(patient.getAddress()));
    }

    public static AddressEmbeddable toAddressEntity(Address address) {
        return new AddressEmbeddable(address.getStreet(), address.getSuburb(), address.getState(), address.getPostcode());
    }
}
