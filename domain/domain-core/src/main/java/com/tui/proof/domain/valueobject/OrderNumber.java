package com.tui.proof.domain.valueobject;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class OrderNumber {
    private final UUID value;
}
