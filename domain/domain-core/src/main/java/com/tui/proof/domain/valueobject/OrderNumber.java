package com.tui.proof.domain.valueobject;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderNumber {
    private final String value;
}
