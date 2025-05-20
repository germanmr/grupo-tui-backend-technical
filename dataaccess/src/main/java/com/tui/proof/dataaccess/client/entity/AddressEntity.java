package com.tui.proof.dataaccess.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Embeddable
public class AddressEntity {
    private String street;
    private String postalCode;
    private String city;
    private String country;
}
