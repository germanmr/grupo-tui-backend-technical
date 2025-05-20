package com.tui.proof.domain.ports;

import com.tui.proof.domain.OrderDomainService;
import com.tui.proof.domain.entity.Client;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.entity.OrderRequest;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.exception.ClientNotFoundException;
import com.tui.proof.domain.exception.ProductNotFoundException;
import com.tui.proof.domain.ports.input.service.OrderApplicationService;
import com.tui.proof.domain.ports.output.repository.ClientRepository;
import com.tui.proof.domain.ports.output.repository.OrderRepository;
import com.tui.proof.domain.ports.output.repository.ProductRepository;
import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;

    @Autowired
    public OrderApplicationServiceImpl(final ClientRepository clientRepository,
                                       final ProductRepository productRepository,
                                       final OrderRepository orderRepository,
                                       final OrderDomainService orderDomainService) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDomainService = orderDomainService;
    }

    @Override
    public Order createOrder(final OrderRequest request) {
        final Client client = checkClient(request.getClientId());
        final Product product = checkProduct(request.getProductId());
        final Order order = orderDomainService.validateAndInitiateOrder(client, product, request.getQuantity());
        return orderRepository.createOrder(order);
    }

    private Client checkClient(final ClientId clientId) {
        return clientRepository.getClientById(
                        ClientId.builder().value(clientId.getValue()).build())
                .orElseThrow(
                        () -> new ClientNotFoundException(
                                "Could not find client with id: " + clientId.getValue()));
    }

    private Product checkProduct(final ProductId productId) {
        return productRepository.getProductById(
                        ProductId.builder().value(productId.getValue()).build())
                .orElseThrow(() -> new ProductNotFoundException(
                        "Could not find product with id: " + productId.getValue()));
    }
}