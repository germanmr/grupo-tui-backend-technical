package com.tui.proof.application.rest;

import com.tui.proof.application.rest.config.TestConfig;
import com.tui.proof.application.rest.mapper.OrderMapperImpl;
import com.tui.proof.application.sampledata.OrderDtoData;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.OrderRequest;
import com.tui.proof.domain.ports.OrderApplicationServiceImpl;
import com.tui.proof.domain.ports.input.service.OrderApplicationService;
import com.tui.proof.domain.sampledata.OrderData;
import com.tui.proof.model.OrderRequestDto;
import com.tui.proof.model.OrderResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {
                TestConfig.class,
                OrderController.class,
                OrderMapperImpl.class,
                OrderApplicationServiceImpl.class
        })
@ActiveProfiles({"test"})
class OrderControllerTest {

    private final OrderRequest orderRequest = OrderData.anyOrderRequest();

    private final OrderRequestDto orderRequestDto = OrderDtoData.anyOrderRequestDto();

    private final Order order = OrderData.anyOrder();

    @Autowired
    private OrderMapperImpl orderMapper;

    @Mock
    private OrderApplicationService orderApplicationService;

    private OrderController orderController;

    @BeforeEach
    void setUp() {
        this.orderController = new OrderController(orderMapper, orderApplicationService);
    }

    @Test
    void can_order_product() {
        Mockito.when(orderApplicationService.createOrder(orderRequest)).thenReturn(order);
        final ResponseEntity<OrderResponseDto> orderResponseDtoResponseEntity =
                orderController.orderPilotes(orderRequestDto);
        assertEquals(OrderDtoData.anyOrderResponseDto(), orderResponseDtoResponseEntity.getBody());
    }
}