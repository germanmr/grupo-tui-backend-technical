package com.tui.proof.application.sampledata;

import com.tui.proof.model.AddressDto;
import com.tui.proof.model.ClientDto;
import com.tui.proof.model.OrderRequestDto;
import com.tui.proof.model.OrderResponseDto;
import com.tui.proof.model.ProductDto;

import java.util.UUID;

public interface OrderDtoData {

    Long CLIENT_ID = 1L;
    Long PRODUCT_ID = 1L;
    UUID ORDER_NUMBER = UUID.fromString("a72253bb-9ac1-497e-b116-2a54ab618966");
    Float ORDER_TOTAL = 22.50F;
    Long QUANTITY = 2L;
    String STREET = "Carrer Sant Eduar 1";
    String POSTAL_CODE = "17538";
    String CITY = "Das";
    String COUNTRY = "España";
    String FIRST_NAME = "Esteve";
    String LAST_NAME = "Clerch";
    String TELEPHONE = "+34 972 123456";
    String PRODUCT_NAME = "PILOTES";
    Float PRICE = 11.25F;

    static OrderRequestDto anyOrderRequestDto() {
        return new OrderRequestDto(
                CLIENT_ID,
                PRODUCT_ID,
                QUANTITY);
    }

    static OrderResponseDto anyOrderResponseDto() {
        return new OrderResponseDto(
                ORDER_NUMBER,
                anyClient(),
                anyProduct(),
                QUANTITY,
                ORDER_TOTAL);
    }

    static ProductDto anyProduct() {
        return new ProductDto(PRODUCT_ID, PRODUCT_NAME, PRICE);
    }

    static ClientDto anyClient() {
        return new ClientDto(
                CLIENT_ID,
                FIRST_NAME,
                LAST_NAME,
                TELEPHONE,
                anyAddressDto());
    }

    static AddressDto anyAddressDto() {
        return new AddressDto(STREET, POSTAL_CODE, CITY, COUNTRY);
    }
}
