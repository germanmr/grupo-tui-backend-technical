package com.tui.proof.application.rest;

import com.tui.proof.application.sampledata.OrderDtoData;
import com.tui.proof.domain.OrderApplication;
import com.tui.proof.model.OrderResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.comparesEqualTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OrderApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
class OrderControllerTestIT {

    @LocalServerPort
    private Integer port;

    @Autowired
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void can_order_product_all_test_cases(
            final Long quantity,
            final OrderResponseDto orderResponseDto) {

        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"clientId\": 1,\"productId\": 1,\"quantity\": \"" + quantity + "\"}")
                .when()
                .post("/order")
                .then()
                .statusCode(200)
                .body("product.productId", comparesEqualTo(matcherLongUnboxing(orderResponseDto.getProduct().getProductId())))
                .body("product.name", comparesEqualTo(orderResponseDto.getProduct().getName()))
                .body("product.price", comparesEqualTo(orderResponseDto.getProduct().getPrice()))
                .body("client.clientId", comparesEqualTo(matcherLongUnboxing(orderResponseDto.getClient().getClientId())))
                .body("client.firstName", comparesEqualTo(orderResponseDto.getClient().getFirstName()))
                .body("client.lastName", comparesEqualTo(orderResponseDto.getClient().getLastName()))
                .body("client.deliveryAddress.city", comparesEqualTo(orderResponseDto.getClient().getDeliveryAddress().getCity()))
                .body("client.deliveryAddress.street", comparesEqualTo(orderResponseDto.getClient().getDeliveryAddress().getStreet()))
                .body("client.deliveryAddress.postalCode", comparesEqualTo(orderResponseDto.getClient().getDeliveryAddress().getPostalCode()))
                .body("quantity", comparesEqualTo(matcherLongUnboxing(orderResponseDto.getQuantity())))
                .body("orderTotal", comparesEqualTo(orderResponseDto.getOrderTotal()));
    }

    @Test
    void cannot_order_product_bad_request() {
        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"clientId\": 1,\"quantity\": 2}")
                .when()
                .post("/order")
                .then()
                .statusCode(400)
                .body("message",
                        comparesEqualTo("productId no debe ser nulo"));
    }


    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(2L, OrderDtoData.anyOrderResponseDto())
        );
    }

    /**
     * Util method to avoid the addition of "L" suffix when unboxing Long
     *
     * @param value
     * @return Integer
     */
    private Integer matcherLongUnboxing(final Long value) {
        return Integer.valueOf(value + "");
    }

}