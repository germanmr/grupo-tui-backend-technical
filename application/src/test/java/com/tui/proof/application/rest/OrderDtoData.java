package com.tui.proof.application.rest;

import com.tui.proof.model.OrderRequestDto;
import com.tui.proof.model.OrderResponseDto;

public interface OrderDtoData {

    Long CLIENT_ID = 1L;
    Long PRODUCT_ID = 1L;
    String ORDER_NUMBER = "123";
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
