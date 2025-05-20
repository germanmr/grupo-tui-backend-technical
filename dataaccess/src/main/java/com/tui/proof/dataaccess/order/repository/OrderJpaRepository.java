package com.tui.proof.dataaccess.order.repository;

import com.tui.proof.dataaccess.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}