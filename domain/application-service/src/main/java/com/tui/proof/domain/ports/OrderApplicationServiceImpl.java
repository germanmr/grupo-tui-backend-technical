package com.tui.proof.domain.ports;

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

import java.util.Optional;

@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderApplicationServiceImpl(final ClientRepository clientRepository,
                                       final ProductRepository productRepository,
                                       final OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(final OrderRequest request) {
        checkClient(request.getClientId());
        checkProduct(request.getProductId());
        return orderRepository.createOrder(
                request.getClientId(),
                request.getProductId(),
                request.getQuantity());
    }

    private void checkClient(final ClientId clientId) {
        final Optional<Client> optional = clientRepository.getClientById(
                ClientId.builder().value(clientId.getValue()).build());
        if (optional.isEmpty()) {
            throw new ClientNotFoundException("Could not find client with id: " + clientId.getValue());
        }
    }

    private void checkProduct(final ProductId productId) {
        final Optional<Product> optional = productRepository.getProductById(
                ProductId.builder().value(productId.getValue()).build());
        if (optional.isEmpty()) {
            throw new ProductNotFoundException("Could not find product with id: " + productId.getValue());
        }
    }
}
