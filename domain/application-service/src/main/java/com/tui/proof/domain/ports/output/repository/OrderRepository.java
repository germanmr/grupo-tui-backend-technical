package com.tui.proof.domain.ports.output.repository;

import com.tui.proof.domain.entity.Order;


public interface OrderRepository {
    Order createOrder(final Order order);
}