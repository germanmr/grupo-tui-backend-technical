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

    @Mapping(source = "client.clientId.value", target = "client.clientId")
    @Mapping(source = "client.firstName.value", target = "client.firstName")
    @Mapping(source = "client.lastName.value", target = "client.lastName")
    @Mapping(source = "client.telephone.value", target = "client.telephone")
    @Mapping(source = "client.deliveryAddress.city.value", target = "client.deliveryAddress.city")
    @Mapping(source = "client.deliveryAddress.street.value", target = "client.deliveryAddress.street")
    @Mapping(source = "client.deliveryAddress.country.value", target = "client.deliveryAddress.country")
    @Mapping(source = "client.deliveryAddress.postalCode.value", target = "client.deliveryAddress.postalCode")
    @Mapping(source = "product.productId.value", target = "product.productId")
    @Mapping(source = "product.name.value", target = "product.name")
    @Mapping(source = "product.price.value", target = "product.price")
    @Mapping(source = "quantity.value", target = "quantity")
    @Mapping(source = "number.value", target = "orderNumber")
    @Mapping(source = "orderTotal.value", target = "orderTotal")
    OrderResponseDto orderToOrderResponseDto(final Order order);

}
