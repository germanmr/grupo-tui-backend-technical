package com.tui.proof.domain.entity;

import com.tui.proof.domain.valueobject.MonetaryAmount;
import com.tui.proof.domain.valueobject.OrderNumber;
import com.tui.proof.domain.valueobject.Quantity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Order {
    private final OrderNumber number;
    private final Client client;
    private final Product product;
    private final Quantity quantity;
    private final MonetaryAmount orderTotal;

}
