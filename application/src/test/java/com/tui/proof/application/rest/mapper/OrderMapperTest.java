package com.tui.proof.application.rest.mapper;

import com.tui.proof.domain.sampledata.OrderData;
import com.tui.proof.application.sampledata.OrderDtoData;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.OrderRequest;
import com.tui.proof.model.OrderRequestDto;
import com.tui.proof.model.OrderResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderMapperTest {

    private final OrderRequestDto orderRequestDto = OrderDtoData.anyOrderRequestDto();
    private final OrderResponseDto orderResponseDto = OrderDtoData.anyOrderResponseDto();

    private final OrderRequest orderRequest = OrderData.anyOrderRequest();
    private final Order order = OrderData.anyOrder();

    private final OrderMapper orderMapper = new OrderMapperImpl();

    @Test
    void can_map_orderRequestDto_To_OrderRequest() {
        assertEquals(this.orderRequest, this.orderMapper.orderRequestDtoToOrderRequest(orderRequestDto));

        assertNull(this.orderMapper.orderRequestDtoToOrderRequest(null));
    }

    @Test
    void can_map_orderProductResponse_To_OrderProductResponseDto() {
        assertEquals(this.orderResponseDto, orderMapper.orderToOrderResponseDto(order));

        assertNull(orderMapper.orderToOrderResponseDto(null));
    }
}