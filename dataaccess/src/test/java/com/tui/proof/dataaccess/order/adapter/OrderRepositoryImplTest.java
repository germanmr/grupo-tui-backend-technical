package com.tui.proof.dataaccess.order.adapter;

import com.tui.proof.dataaccess.OrderDataAccessData;
import com.tui.proof.dataaccess.config.DataAccessTestConfiguration;
import com.tui.proof.dataaccess.order.entity.OrderEntity;
import com.tui.proof.dataaccess.order.mapper.OrderDataAccessMapper;
import com.tui.proof.dataaccess.order.mapper.OrderDataAccessMapperImpl;
import com.tui.proof.dataaccess.order.repository.OrderJpaRepository;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.sampledata.OrderData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {
        DataAccessTestConfiguration.class,
        OrderDataAccessMapperImpl.class})
class OrderRepositoryImplTest {

    private final Order order = OrderData.anyOrder();

    private final OrderEntity orderEntity = OrderDataAccessData.anyOrderEntity();

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private OrderDataAccessMapper orderDataAccessMapper;

    private OrderRepositoryImpl orderRepository;

    @BeforeAll
    void setUp() {
        this.orderRepository = new OrderRepositoryImpl(orderDataAccessMapper, orderJpaRepository);
    }

    @Test
    void can_create_order() {
        when(orderJpaRepository.save(any())).thenReturn(orderEntity);
        assertEquals(order, orderRepository.createOrder(order));
    }

}