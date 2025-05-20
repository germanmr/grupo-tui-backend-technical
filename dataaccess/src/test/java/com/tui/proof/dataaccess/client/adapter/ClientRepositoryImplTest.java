package com.tui.proof.dataaccess.client.adapter;

import com.tui.proof.dataaccess.OrderData;
import com.tui.proof.dataaccess.OrderDataAccessData;
import com.tui.proof.dataaccess.client.entity.ClientEntity;
import com.tui.proof.dataaccess.client.mapper.ClientDataAccessMapper;
import com.tui.proof.dataaccess.client.mapper.ClientDataAccessMapperImpl;
import com.tui.proof.dataaccess.client.repository.ClientJpaRepository;
import com.tui.proof.dataaccess.config.DataAccessTestConfiguration;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.valueobject.ClientId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {
        DataAccessTestConfiguration.class,
        ClientDataAccessMapperImpl.class})
class ClientRepositoryImplTest {

    private final Long clientIdValue = 1L;

    private final Client client = OrderData.anyClient();

    private final ClientEntity clientEntity = OrderDataAccessData.anyClientEntity();

    @Autowired
    private ClientJpaRepository clientJpaRepository;
    @Autowired
    private ClientDataAccessMapper clientDataAccessMapper;

    private ClientRepositoryImpl clientRepository;

    @BeforeAll
    void setUp() {
        this.clientRepository = new ClientRepositoryImpl(clientJpaRepository, clientDataAccessMapper);
    }

    @Test
    void can_get_by_id() {
        when(this.clientJpaRepository.findById(clientIdValue))
                .thenReturn(Optional.of(clientEntity));
        assertEquals(Optional.of(client),
                this.clientRepository.getClientById(ClientId.builder().value(clientIdValue).build()));
    }

    @Test
    void can_get_by_id_no_client() {
        when(this.clientJpaRepository.findById(clientIdValue)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), this.clientRepository
                .getClientById(ClientId.builder().value(clientIdValue).build()));
    }

}