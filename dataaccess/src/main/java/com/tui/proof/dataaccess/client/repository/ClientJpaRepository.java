package com.tui.proof.dataaccess.client.repository;

import com.tui.proof.dataaccess.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity,Long> {
}
