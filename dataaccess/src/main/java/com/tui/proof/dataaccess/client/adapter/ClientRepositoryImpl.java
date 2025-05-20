package com.tui.proof.dataaccess.client.adapter;

import com.tui.proof.dataaccess.client.mapper.ClientDataAccessMapper;
import com.tui.proof.dataaccess.client.repository.ClientJpaRepository;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.ports.output.repository.ClientRepository;
import com.tui.proof.domain.valueobject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientDataAccessMapper clientDataAccessMapper;

    @Autowired
    public ClientRepositoryImpl(final ClientJpaRepository clientJpaRepository,
                                final ClientDataAccessMapper clientDataAccessMapper) {
        this.clientJpaRepository = clientJpaRepository;
        this.clientDataAccessMapper = clientDataAccessMapper;
    }

    @Override
    public Optional<Client> getClientById(final ClientId clientId) {
        return this.clientDataAccessMapper.optionalClientEntityToOptionalClient(
                this.clientJpaRepository.findById(clientId.getValue()));
    }
}
