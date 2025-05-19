package com.tui.proof.domain.valueobject;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Telephone {
    private final String value;
}
