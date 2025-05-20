package com.tui.proof.domain.config;

import com.tui.proof.application.rest.mapper.OrderMapper;
import com.tui.proof.application.rest.mapper.OrderMapperImpl;
import com.tui.proof.dataaccess.client.mapper.ClientDataAccessMapper;
import com.tui.proof.dataaccess.client.mapper.ClientDataAccessMapperImpl;
import com.tui.proof.dataaccess.order.mapper.OrderDataAccessMapper;
import com.tui.proof.dataaccess.order.mapper.OrderDataAccessMapperImpl;
import com.tui.proof.dataaccess.product.mapper.ProductDataAccessMapper;
import com.tui.proof.dataaccess.product.mapper.ProductDataAccessMapperImpl;
import com.tui.proof.domain.OrderDomainService;
import com.tui.proof.domain.OrderDomainServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.tui.proof.dataaccess"})
@EntityScan(basePackages = {"com.tui.proof.dataaccess"})
@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

    @Bean
    public OrderMapper rateMapper() {
        return new OrderMapperImpl();
    }

    @Bean
    public ClientDataAccessMapper clientDataAccessMapper() {
        return new ClientDataAccessMapperImpl();
    }

    @Bean
    public ProductDataAccessMapper productDataAccessMapper() {
        return new ProductDataAccessMapperImpl();
    }

    @Bean
    public OrderDataAccessMapper orderDataAccessMapper() {
        return new OrderDataAccessMapperImpl();
    }

}
