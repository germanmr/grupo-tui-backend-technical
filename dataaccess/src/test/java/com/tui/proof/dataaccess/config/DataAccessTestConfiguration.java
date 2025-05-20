package com.tui.proof.dataaccess.config;

import com.tui.proof.dataaccess.client.repository.ClientJpaRepository;
import com.tui.proof.dataaccess.order.repository.OrderJpaRepository;
import com.tui.proof.dataaccess.product.repository.ProductJpaRepository;
import com.tui.proof.domain.OrderDomainService;
import com.tui.proof.domain.OrderDomainServiceImpl;
import com.tui.proof.domain.ports.output.repository.ClientRepository;
import com.tui.proof.domain.ports.output.repository.OrderRepository;
import com.tui.proof.domain.ports.output.repository.ProductRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = "com.tui.proof.domain.ports")
@Configuration
public class DataAccessTestConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public ProductJpaRepository productJpaRepository() {
        return Mockito.mock(ProductJpaRepository.class);
    }

    @Bean
    public ClientRepository clientRepository() {
        return Mockito.mock(ClientRepository.class);
    }

    @Bean
    public ClientJpaRepository clientJpaRepository() {
        return Mockito.mock(ClientJpaRepository.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public OrderJpaRepository orderJpaRepository() {
        return Mockito.mock(OrderJpaRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

}
