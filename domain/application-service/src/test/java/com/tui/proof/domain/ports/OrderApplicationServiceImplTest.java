package com.tui.proof.domain.ports;

import com.tui.proof.domain.OrderDomainService;
import com.tui.proof.domain.config.TestConfig;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.OrderRequest;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.exception.ClientNotFoundException;
import com.tui.proof.domain.exception.ProductNotFoundException;
import com.tui.proof.domain.ports.output.repository.ClientRepository;
import com.tui.proof.domain.ports.output.repository.OrderRepository;
import com.tui.proof.domain.ports.output.repository.ProductRepository;
import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.ProductId;
import com.tui.proof.domain.sampledata.OrderData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {
        TestConfig.class
})
class OrderApplicationServiceImplTest {

    private final Long clientId = 1L;
    private final Long productId = 1L;

    private final Client client = OrderData.anyClient();

    private final Product product = OrderData.anyProduct();

    private final OrderRequest orderRequest = OrderData.anyOrderRequest();

    private final Order order = OrderData.anyOrder();

    private OrderApplicationServiceImpl orderApplicationService;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDomainService orderDomainService;

    @BeforeEach
    void init() {
        this.orderApplicationService = new OrderApplicationServiceImpl(
                clientRepository,
                productRepository,
                orderRepository,
                orderDomainService);
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
    void can_order_pilotes() {
        when(this.clientRepository.getClientById(ClientId.builder().value(clientId).build()))
                .thenReturn(Optional.of(client));
        when(this.productRepository.getProductById(ProductId.builder().value(productId).build()))
                .thenReturn(Optional.of(product));
        when(this.orderRepository.createOrder(any())).thenReturn(order);
        assertEquals(order, this.orderApplicationService.createOrder(orderRequest));
    }
}