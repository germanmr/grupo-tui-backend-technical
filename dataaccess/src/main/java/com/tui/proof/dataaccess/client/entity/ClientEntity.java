package com.tui.proof.dataaccess.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "clients")
@Entity
public class ClientEntity {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    @Embedded
    private AddressEntity deliveryAddress;
}
