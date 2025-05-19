package com.tui.proof.domain.ports.output.repository;

import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.valueobject.ClientId;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> getClientById(final ClientId clientId);
}
