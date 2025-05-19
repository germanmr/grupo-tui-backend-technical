package com.tui.proof.domain.valueobject;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public final class MonetaryAmount {
    private final BigDecimal value;
}
