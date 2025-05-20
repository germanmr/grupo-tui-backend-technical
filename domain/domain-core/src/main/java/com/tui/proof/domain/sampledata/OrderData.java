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


public interface OrderData {

    Long CLIENT_ID_VALUE = 1L;
    Long PRODUCT_ID_VALUE = 1L;
    String ORDER_NUMBER_VALUE = "123";
    ClientId CLIENT_ID = ClientId.builder().value(CLIENT_ID_VALUE).build();
    ProductId PRODUCT_ID = ProductId.builder().value(PRODUCT_ID_VALUE).build();
    Client CLIENT = Client.builder()
            .clientId(CLIENT_ID)
            .firstName(Name.builder().value("Esteve").build())
            .lastName(Name.builder().value("Clerch").build())
            .telephone(Telephone.builder().value("+34 972 123456").build())
            .deliveryAddress(Address.builder()
                    .country(Country.builder().value("España").build())
                    .postalcode(PostalCode.builder().value("17538").build())
                    .city(City.builder().value("Das").build())
                    .street(Street.builder().value("Carrer Sant Eduar 1").build())
                    .build())
            .build();

    BigDecimal PRICE = new BigDecimal("10.25");
    BigDecimal ORDER_TOTAL = new BigDecimal("22.50");

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
                .client(CLIENT)
                .product(Product.builder()
                        .productId(ProductId.builder().value(PRODUCT_ID_VALUE).build())
                        .name(Name.builder().value("S'aguiat de pilotes").build())
                        .price(MonetaryAmount.builder().value(PRICE).build())
                        .build())
                .quantity(Quantity.builder().value(2L).build())
                .orderTotal(MonetaryAmount.builder().value(ORDER_TOTAL).build())
                .build();
    }
}
