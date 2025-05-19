package com.tui.proof.domain.ports.input.service;

import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.OrderRequest;

public interface OrderApplicationService {

    Order createOrder(final OrderRequest orderRequest);
}