package com.tui.proof.application.rest.config;

import com.tui.proof.domain.ports.output.repository.ClientRepository;
import com.tui.proof.domain.ports.output.repository.OrderRepository;
import com.tui.proof.domain.ports.output.repository.ProductRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ClientRepository clientRepository() {
        return Mockito.mock(ClientRepository.class);
    }

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

}
