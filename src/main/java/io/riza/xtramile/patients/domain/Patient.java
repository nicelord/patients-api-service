package io.riza.xtramile.patients.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

import java.time.LocalDate;


@With
@Getter
@AllArgsConstructor
public class Patient {
    private final Long id;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private String phone;
    private Address address;

    public void changeInfo(String firstName,
                           String lastName,
                           LocalDate dob,
                           Gender gender,
                           String phone,
                           Address address) {

        if (firstName != null) {
            this.firstName = firstName;
        }
        if (lastName != null) {
            this.lastName = lastName;
        }
        if (dob != null) {
            this.dob = dob;
        }
        if (gender != null) {
            this.gender = gender;
        }
        if (phone != null) {
            this.phone = phone;
        }
        if (address != null) {
            this.address = changeAddress(address);
        }
    }

    private Address changeAddress(Address address) {
        Address result = this.getAddress();
        if (address.getStreet() != null) {
            result = result.withStreet(address.getStreet());
        }
        if (address.getSuburb() != null) {
            result = result.withSuburb(address.getSuburb());
        }
        if (address.getState() != null) {
            result = result.withState(address.getState());
        }
        if (address.getPostcode() != null) {
            result = result.withPostcode(address.getPostcode());
        }

        return result;
    }
}
