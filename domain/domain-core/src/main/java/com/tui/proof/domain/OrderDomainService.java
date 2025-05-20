package com.tui.proof.domain;

import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.Quantity;

public interface OrderDomainService {

    Order validateAndInitiateOrder(final Client client, final Product product, final Quantity quantity);
}