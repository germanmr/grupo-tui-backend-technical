package com.tui.proof.dataaccess;

import com.tui.proof.dataaccess.client.entity.AddressEntity;
import com.tui.proof.dataaccess.client.entity.ClientEntity;
import com.tui.proof.dataaccess.order.entity.OrderEntity;
import com.tui.proof.dataaccess.product.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderDataAccessData {

    Long CLIENT_ID_VALUE = 1L;
    String CLIENT_FIRST_NAME_VALUE = "Esteve";
    String CLIENT_LAST_NAME_VALUE = "Clerch";
    String TELEPHONE_VALUE = "+34 972 123456";
    String COUNTRY_VALUE = "España";
    String CITY_VALUE = "Das";
    String POSTAL_CODE_VALUE = "17538";
    String STREET_VALUE = "Carrer Sant Eduar 1";

    Long PRODUCT_ID_VALUE = 1L;
    String PRODUCT_NAME_VALUE = "S'aguiat de pilotes";
    BigDecimal PRICE = new BigDecimal("11.25");

    UUID ORDER_NUMBER = UUID.fromString("a72253bb-9ac1-497e-b116-2a54ab618966");
    Long QUANTITY = 2L;

    static ProductEntity anyProductEntity() {
        return ProductEntity.builder()
                .id(PRODUCT_ID_VALUE)
                .name(PRODUCT_NAME_VALUE)
                .price(PRICE)
                .build();
    }

    static AddressEntity anyAddress() {
        return AddressEntity.builder()
                .country(COUNTRY_VALUE)
                .postalCode(POSTAL_CODE_VALUE)
                .city(CITY_VALUE)
                .street(STREET_VALUE)
                .build();
    }

    static ClientEntity anyClientEntity() {
        return ClientEntity.builder()
                .id(CLIENT_ID_VALUE)
                .firstName(CLIENT_FIRST_NAME_VALUE)
                .lastName(CLIENT_LAST_NAME_VALUE)
                .telephone(TELEPHONE_VALUE)
                .deliveryAddress(OrderDataAccessData.anyAddress())
                .build();
    }

    static OrderEntity anyOrderEntity() {
        return OrderEntity.builder()
                .id(ORDER_NUMBER)
                .clientEntity(anyClientEntity())
                .productEntity(anyProductEntity())
                .quantity(QUANTITY)
                .orderTotal(PRICE.multiply(new BigDecimal(QUANTITY)))
                .build();
    }

}