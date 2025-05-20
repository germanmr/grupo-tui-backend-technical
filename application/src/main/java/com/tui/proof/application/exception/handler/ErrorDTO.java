package com.tui.proof.application.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public final class ErrorDTO {
    private final String code;
    private final String message;
}
