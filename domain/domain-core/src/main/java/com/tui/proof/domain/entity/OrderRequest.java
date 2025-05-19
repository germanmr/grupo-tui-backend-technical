package com.tui.proof.domain.entity;

import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.ProductId;
import com.tui.proof.domain.valueobject.Quantity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class OrderRequest {
    private final ClientId clientId;
    private final ProductId productId;
    private final Quantity quantity;
}
