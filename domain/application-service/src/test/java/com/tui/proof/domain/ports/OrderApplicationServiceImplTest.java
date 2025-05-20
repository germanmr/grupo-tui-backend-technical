package com.tui.proof.domain.ports;

import com.tui.proof.domain.config.TestConfig;
import com.tui.proof.domain.entity.Address;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.OrderRequest;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.exception.ClientNotFoundException;
import com.tui.proof.domain.exception.ProductNotFoundException;
import com.tui.proof.domain.ports.output.repository.ClientRepository;
import com.tui.proof.domain.ports.output.repository.OrderRepository;
import com.tui.proof.domain.ports.output.repository.ProductRepository;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        TestConfig.class
})
class OrderApplicationServiceImplTest {

    private final Long clientId = 1L;
    private final Long productId = 1L;
    private final BigDecimal price = new BigDecimal("11.25");

    private final Client client = Client.builder()
            .clientId(ClientId.builder().value(clientId).build())
            .firstName(Name.builder().value("Esteve").build())
            .lastName(Name.builder().value("Clerch").build())
            .telephone(Telephone.builder().value("+34 971 123456").build())
            .deliveryAddress(Address.builder()
                    .country(Country.builder().value("España").build())
                    .city(City.builder().value("Das").build())
                    .postcode(PostalCode.builder().value("17538").build())
                    .street(Street.builder().value("Carrer Sant Eduar 1").build())
                    .build())
            .build();

    private final Product product = Product.builder()
            .productId(ProductId.builder().value(productId).build())
            .name(Name.builder().value("S'aguiat de pilotes").build())
            .price(MonetaryAmount.builder().value(price).build())
            .build();


    private final OrderRequest orderRequest =
            OrderRequest.builder()
                    .clientId(ClientId.builder().value(clientId).build())
                    .productId(ProductId.builder().value(productId).build())
                    .quantity(Quantity.builder().value(2L).build())
                    .build();
    private final Order order = Order.builder()
            .number(OrderNumber.builder().value("1").build())
            .client(client)
            .product(product)
            .quantity(Quantity.builder().value(2L).build())
            .orderTotal(MonetaryAmount.builder().value(new BigDecimal("22.50")).build())
            .build();

    private OrderApplicationServiceImpl orderApplicationService;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void init() {
        this.orderApplicationService = new OrderApplicationServiceImpl(
                clientRepository,
                productRepository,
                orderRepository);
    }

    @Test
    void cannot_order_unexistent_client() {
        when(this.clientRepository.getClientById(ClientId.builder().value(clientId).build())).thenReturn(Optional.empty());
        final ClientNotFoundException clientNotFoundException =
                assertThrows(ClientNotFoundException.class, () -> this.orderApplicationService.createOrder(orderRequest));
        assertEquals("Could not find client with id: 1", clientNotFoundException.getMessage());
    }

    @Test
    void cannot_order_unexistent_product() {
        when(this.clientRepository.getClientById(ClientId.builder().value(clientId).build())).thenReturn(Optional.of(client));
        final ProductNotFoundException productNotFoundException =
                assertThrows(ProductNotFoundException.class, () -> this.orderApplicationService.createOrder(orderRequest));
        assertEquals("Could not find product with id: 1", productNotFoundException.getMessage());
    }

    @Test
    void can_order_product() {
        when(this.clientRepository.getClientById(ClientId.builder().value(clientId).build()))
                .thenReturn(Optional.of(client));
        when(this.productRepository.getProductById(ProductId.builder().value(productId).build()))
                .thenReturn(Optional.of(product));
        when(this.orderRepository.createOrder(
                ClientId.builder().value(clientId).build(),
                ProductId.builder().value(productId).build(),
                Quantity.builder().value(2L).build()))
                .thenReturn(order);
        assertEquals(order, this.orderApplicationService.createOrder(orderRequest));
    }
}