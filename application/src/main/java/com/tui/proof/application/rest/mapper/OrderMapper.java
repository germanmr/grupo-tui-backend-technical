package com.tui.proof.application.rest.mapper;

import com.tui.proof.domain.entity.OrderRequest;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.model.OrderRequestDto;
import com.tui.proof.model.OrderResponseDto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper
@MapperConfig(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

    @Mapping(source = "clientId", target = "clientId.value")
    @Mapping(source = "productId", target = "productId.value")
    @Mapping(source = "quantity", target = "quantity.value")
    OrderRequest orderRequestDtoToOrderRequest(final OrderRequestDto orderRequest);

    @Mapping(source = "client.clientId.value", target = "clientId")
    @Mapping(source = "product.productId.value", target = "productId")
    @Mapping(source = "quantity.value", target = "quantity")
    @Mapping(source = "number.value", target = "orderNumber")
    @Mapping(source = "orderTotal.value", target = "orderTotal")
    OrderResponseDto orderToOrderResponseDto(final Order order);

}
