package com.tui.proof.application.rest;

import com.tui.proof.api.OrderApi;
import com.tui.proof.application.exception.handler.GlobalExceptionHandler;
import com.tui.proof.application.rest.mapper.OrderMapper;
import com.tui.proof.domain.ports.input.service.OrderApplicationService;
import com.tui.proof.model.OrderRequestDto;
import com.tui.proof.model.OrderResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class OrderController extends GlobalExceptionHandler implements OrderApi {

    private final OrderMapper orderMapper;
    private final OrderApplicationService orderApplicationService;

    public OrderController(final OrderMapper orderMapper,
                           final OrderApplicationService orderApplicationService) {
        this.orderMapper = orderMapper;
        this.orderApplicationService = orderApplicationService;
    }

    @Override
    public ResponseEntity<OrderResponseDto> orderPilotes(@RequestBody OrderRequestDto orderRequestDto) {
        log.info("Ordering 'pilotes' product: {} for client: {}, qunatity: {}",
                orderRequestDto.getProductId(), orderRequestDto.getClientId(), orderRequestDto.getQuantity());
        return new ResponseEntity<OrderResponseDto>(
                this.orderMapper.orderToOrderResponseDto(
                        this.orderApplicationService.createOrder(
                                this.orderMapper.orderRequestDtoToOrderRequest(orderRequestDto)))
                , HttpStatus.OK);
    }
}
