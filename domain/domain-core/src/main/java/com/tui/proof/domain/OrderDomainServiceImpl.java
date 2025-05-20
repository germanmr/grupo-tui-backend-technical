package com.tui.proof.domain;

import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.MonetaryAmount;
import com.tui.proof.domain.valueobject.Quantity;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public Order validateAndInitiateOrder(final Client client, final Product product, final Quantity quantity) {
        return Order.builder()
                .client(client)
                .product(product)
                .quantity(quantity)
                .orderTotal(calculateOrderTotal(product, quantity))
                .build();
    }

    private MonetaryAmount calculateOrderTotal(final Product product, final Quantity quantity) {
        return MonetaryAmount.builder()
                .value(product.getPrice().getValue().multiply(new BigDecimal(quantity.getValue())))
                .build();
    }
}