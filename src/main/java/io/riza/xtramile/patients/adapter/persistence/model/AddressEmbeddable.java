package io.riza.xtramile.patients.adapter.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddressEmbeddable {
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String suburb;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String postcode;
}
