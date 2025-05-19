package com.tui.proof.domain.valueobject;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Country {
    private final String value;
}
