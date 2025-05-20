package com.tui.proof.application.rest;

import com.tui.proof.model.OrderRequestDto;
import com.tui.proof.model.OrderResponseDto;

import java.util.UUID;

public interface OrderDtoData {

    Long CLIENT_ID = 1L;
    Long PRODUCT_ID = 1L;
    UUID ORDER_NUMBER = UUID.fromString("a72253bb-9ac1-497e-b116-2a54ab618966");
    Float ORDER_TOTAL = 22.50F;
    Long QUANTITY = 2L;

    static OrderRequestDto anyOrderRequestDto() {
        return new OrderRequestDto(
                CLIENT_ID,
                PRODUCT_ID,
                QUANTITY);
    }

    static OrderResponseDto anyOrderResponseDto() {
        return new OrderResponseDto(
                ORDER_NUMBER,
                CLIENT_ID,
                PRODUCT_ID,
                QUANTITY,
                ORDER_TOTAL);
    }
}
