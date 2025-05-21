package com.tui.proof.domain.sampledata;

import com.tui.proof.domain.entity.Address;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.OrderRequest;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.City;
import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.Country;
import com.tui.proof.domain.valueobject.MonetaryAmount;
import com.tui.proof.domain.valueobject.Name;
import com.tui.proof.domain.valueobject.OrderNumber;
import com.tui.proof.domain.valueobject.PostalCode;
import com.tui.proof.domain.valueobject.ProductId;
import com.tui.proof.domain.valueobject.Quantity;
import com.tui.proof.domain.valueobject.Street;
import com.tui.proof.domain.valueobject.Telephone;


import java.math.BigDecimal;
import java.util.UUID;

/**
 * This is an example data that can be throughout the modules,
 * in a real life project these data should not be placed here
 */
public interface OrderData {

    Long CLIENT_ID_VALUE = 1L;
    String COUNTRY_VALUE = "España";
    String CITY_VALUE = "Das";
    String POSTAL_CODE = "17538";
    String STREET_VALUE = "Carrer Sant Eduar 1";

    ClientId CLIENT_ID = ClientId.builder().value(CLIENT_ID_VALUE).build();

    Long PRODUCT_ID_VALUE = 1L;
    ProductId PRODUCT_ID = ProductId.builder().value(PRODUCT_ID_VALUE).build();
    BigDecimal PRODUCT_PRICE = new BigDecimal("11.25");

    UUID ORDER_NUMBER_VALUE = UUID.fromString("a72253bb-9ac1-497e-b116-2a54ab618966");
    BigDecimal ORDER_TOTAL = new BigDecimal("22.50");
    Long QUANTITY = 2L;


    static OrderRequest anyOrderRequest() {
        return OrderRequest.builder()
                .clientId(CLIENT_ID)
                .productId(PRODUCT_ID)
                .quantity(Quantity.builder().value(2L).build())
                .build();
    }

    static Order anyOrder() {
        return Order.builder()
                .number(OrderNumber.builder().value(ORDER_NUMBER_VALUE).build())
                .client(anyClient())
                .product(anyProduct())
                .quantity(Quantity.builder().value(QUANTITY).build())
                .orderTotal(MonetaryAmount.builder().value(ORDER_TOTAL).build())
                .build();
    }


    static Client anyClient() {
        return Client.builder()
                .clientId(CLIENT_ID)
                .firstName(Name.builder().value("Esteve").build())
                .lastName(Name.builder().value("Clerch").build())
                .telephone(Telephone.builder().value("+34 972 123456").build())
                .deliveryAddress(anyAddress())
                .build();
    }

    static Address anyAddress() {
        return Address.builder()
                .country(Country.builder().value(COUNTRY_VALUE).build())
                .city(City.builder().value(CITY_VALUE).build())
                .postalCode(PostalCode.builder().value(POSTAL_CODE).build())
                .street(Street.builder().value(STREET_VALUE).build())
                .build();
    }

    static Product anyProduct() {
        return Product.builder()
                .productId(ProductId.builder().value(PRODUCT_ID_VALUE).build())
                .name(Name.builder().value("PILOTES").build())
                .price(MonetaryAmount.builder().value(PRODUCT_PRICE).build())
                .build();
    }

}
