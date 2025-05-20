package com.tui.proof.dataaccess;

import com.tui.proof.domain.entity.Address;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
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

/**
 * This is an example data that can be throughout the modules,
 * in a real life project these data should not be placed here
 */
public interface OrderData {

    String ORDER_NUMBER = "123";
    Long PRODUCT_ID = 1L;
    BigDecimal ORDER_TOTAL = new BigDecimal("22.50");
    Long CLIENT_ID_VALUE = 1L;
    String COUNTRY_VALUE = "España";
    String CITY_VALUE = "Das";
    String POSTAL_CODE = "17538";
    String STREET_VALUE = "Carrer Sant Eduar 1";
    BigDecimal PRODUCT_PRICE = new BigDecimal("11.25");

    static Client anyClient() {
        return Client.builder()
                .clientId(ClientId.builder().value(CLIENT_ID_VALUE).build())
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
                .postalcode(PostalCode.builder().value(POSTAL_CODE).build())
                .street(Street.builder().value(STREET_VALUE).build())
                .build();
    }

    static Product anyProduct() {
        return Product.builder()
                .productId(ProductId.builder().value(PRODUCT_ID).build())
                .name(Name.builder().value("S'aguiat de pilotes").build())
                .price(MonetaryAmount.builder().value(PRODUCT_PRICE).build())
                .build();
    }

    static Order anyOrder() {
        return Order.builder()
                .number(OrderNumber.builder().value(ORDER_NUMBER).build())
                .client(OrderData.anyClient())
                .product(OrderData.anyProduct())
                .quantity(Quantity.builder().value(2L).build())
                .orderTotal(MonetaryAmount.builder().value(ORDER_TOTAL).build())
                .build();
    }


}
