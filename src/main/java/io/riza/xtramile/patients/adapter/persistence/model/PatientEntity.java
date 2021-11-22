package io.riza.xtramile.patients.adapter.persistence.model;

import io.riza.xtramile.patients.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {
    @Id
    @GeneratedValue
    private Long pid;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;

    @Embedded
    @CollectionTable(name = "tbl_address")
    private AddressEmbeddable address;
}
