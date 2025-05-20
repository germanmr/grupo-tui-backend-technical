package com.tui.proof.dataaccess.client.mapper;

import com.tui.proof.dataaccess.client.entity.ClientEntity;
import com.tui.proof.domain.entity.Address;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.valueobject.City;
import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.Country;
import com.tui.proof.domain.valueobject.Name;
import com.tui.proof.domain.valueobject.PostalCode;
import com.tui.proof.domain.valueobject.Street;
import com.tui.proof.domain.valueobject.Telephone;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Mapper
public interface ClientDataAccessMapper {

    default Optional<Client> optionalClientEntityToOptionalClient(final Optional<ClientEntity> clientEntity) {
        return clientEntity.map(ce ->
                Client.builder()
                        .clientId(ClientId.builder().value(ce.getId()).build())
                        .firstName(Name.builder().value(ce.getFirstName()).build())
                        .lastName(Name.builder().value(ce.getLastName()).build())
                        .telephone(Telephone.builder().value(ce.getTelephone()).build())
                        .deliveryAddress(Address.builder()
                                .country(Country.builder().value(ce.getDeliveryAddress().getCountry()).build())
                                .city(City.builder().value(ce.getDeliveryAddress().getCity()).build())
                                .postalcode(PostalCode.builder().value(ce.getDeliveryAddress().getPostalCode()).build())
                                .street(Street.builder().value(ce.getDeliveryAddress().getStreet()).build())
                                .build())
                        .build()
        );
    }
}
