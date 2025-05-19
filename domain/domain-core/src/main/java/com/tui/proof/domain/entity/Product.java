package com.tui.proof.domain.entity;

import com.tui.proof.domain.valueobject.MonetaryAmount;
import com.tui.proof.domain.valueobject.Name;
import com.tui.proof.domain.valueobject.ProductId;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Product {
    private final ProductId productId;
    private final Name name;
    private final MonetaryAmount price;
}