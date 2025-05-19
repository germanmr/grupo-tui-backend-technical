package com.tui.proof.domain.valueobject;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class PostalCode {
    private final String value;
}
