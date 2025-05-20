package com.tui.proof.domain;

import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.MonetaryAmount;
import com.tui.proof.domain.valueobject.OrderNumber;
import com.tui.proof.domain.valueobject.Quantity;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public Order validateAndInitiateOrder(final Client client, final Product product, final Quantity quantity) {
        final OrderNumber ordernumber = generateOrderNumber();
        final MonetaryAmount orderTotal = calculateOrderTotal(product, quantity);
        return Order.builder()
                .number(ordernumber)
                .client(client)
                .product(product)
                .quantity(quantity)
                .orderTotal(orderTotal)
                .build();
    }

    /**
     * This method can be later expand to generate a Custom Order Number:
     * timestamp + branch + customer
     * or any legal requirement
     *
     * @return
     */
    private OrderNumber generateOrderNumber() {
        return OrderNumber.builder().value(UUID.randomUUID()).build();
    }

    /**
     * This method can be expanded in order to add more information to Product price calculation
     *
     * @param product
     * @param quantity
     * @return
     */
    private MonetaryAmount calculateOrderTotal(final Product product, final Quantity quantity) {
        return MonetaryAmount.builder()
                .value(product.getPrice().getValue().multiply(new BigDecimal(quantity.getValue())))
                .build();
    }
}