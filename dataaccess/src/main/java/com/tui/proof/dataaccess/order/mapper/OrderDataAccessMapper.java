package com.tui.proof.dataaccess.order.mapper;

import com.tui.proof.dataaccess.client.entity.AddressEntity;
import com.tui.proof.dataaccess.client.entity.ClientEntity;
import com.tui.proof.dataaccess.order.entity.OrderEntity;
import com.tui.proof.dataaccess.product.entity.ProductEntity;
import com.tui.proof.domain.entity.Address;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.City;
import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.Country;
import com.tui.proof.domain.valueobject.Name;
import com.tui.proof.domain.valueobject.OrderNumber;
import com.tui.proof.domain.valueobject.MonetaryAmount;
import com.tui.proof.domain.valueobject.PostalCode;
import com.tui.proof.domain.valueobject.ProductId;
import com.tui.proof.domain.valueobject.Quantity;
import com.tui.proof.domain.valueobject.Street;
import com.tui.proof.domain.valueobject.Telephone;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface OrderDataAccessMapper {

    default Order orderEntityToOrder(final OrderEntity orderEntity) {
        return Order.builder()
                .number(OrderNumber.builder().value(orderEntity.getId()).build())
                .client(Client.builder()
                        .clientId(ClientId.builder().value(orderEntity.getClientEntity().getId()).build())
                        .firstName(Name.builder().value(orderEntity.getClientEntity().getFirstName()).build())
                        .lastName(Name.builder().value(orderEntity.getClientEntity().getLastName()).build())
                        .telephone(Telephone.builder().value(orderEntity.getClientEntity().getTelephone()).build())
                        .deliveryAddress(Address.builder()
                                .country(Country.builder().value(orderEntity.getClientEntity().getDeliveryAddress().getCountry()).build())
                                .city(City.builder().value(orderEntity.getClientEntity().getDeliveryAddress().getCity()).build())
                                .postalCode(PostalCode.builder().value(orderEntity.getClientEntity().getDeliveryAddress().getPostalCode()).build())
                                .street(Street.builder().value(orderEntity.getClientEntity().getDeliveryAddress().getStreet()).build())
                                .build())
                        .build()

                )
                .product(Product.builder()
                        .productId(ProductId.builder().value(orderEntity.getProductEntity().getId()).build())
                        .name(Name.builder().value(orderEntity.getProductEntity().getName()).build())
                        .price(MonetaryAmount.builder().value(orderEntity.getProductEntity().getPrice()).build())
                        .build())
                .orderTotal(MonetaryAmount.builder().value(orderEntity.getOrderTotal()).build())
                .quantity(Quantity.builder().value(orderEntity.getQuantity()).build())
                .build();
    }

    default OrderEntity orderToOrderEntity(final Order order) {
        return OrderEntity.builder()
                .id(order.getNumber().getValue())
                .clientEntity(ClientEntity.builder()
                        .id(order.getClient().getClientId().getValue())
                        .firstName(order.getClient().getFirstName().getValue())
                        .lastName(order.getClient().getLastName().getValue())
                        .telephone(order.getClient().getTelephone().getValue())
                        .deliveryAddress(AddressEntity.builder()
                                .city(order.getClient().getDeliveryAddress().getCity().getValue())
                                .street(order.getClient().getDeliveryAddress().getStreet().getValue())
                                .postalCode(order.getClient().getDeliveryAddress().getPostalCode().getValue())
                                .country(order.getClient().getDeliveryAddress().getCountry().getValue())
                                .build())
                        .build())
                .productEntity(ProductEntity.builder()
                        .id(order.getProduct().getProductId().getValue())
                        .name(order.getProduct().getName().getValue())
                        .price(order.getProduct().getPrice().getValue())
                        .build())
                .orderTotal(order.getOrderTotal().getValue())
                .quantity(order.getQuantity().getValue())
                .build();
    }
}