package com.tui.proof.domain.ports.output.repository;

import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.ProductId;
import com.tui.proof.domain.valueobject.Quantity;

public interface OrderRepository {
    Order createOrder(final ClientId clientId, final ProductId productId, final Quantity quantity);
}