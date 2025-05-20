package com.tui.proof.dataaccess.order.adapter;

import com.tui.proof.dataaccess.order.mapper.OrderDataAccessMapper;
import com.tui.proof.dataaccess.order.repository.OrderJpaRepository;
import com.tui.proof.domain.entity.Order;
import com.tui.proof.domain.ports.output.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDataAccessMapper orderDataAccessMapper;
    private final OrderJpaRepository orderJpaRepository;

    @Autowired
    public OrderRepositoryImpl(OrderDataAccessMapper orderDataAccessMapper, OrderJpaRepository orderJpaRepository) {
        this.orderDataAccessMapper = orderDataAccessMapper;
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order createOrder(final Order order) {
        return this.orderDataAccessMapper.orderEntityToOrder(
                this.orderJpaRepository.save(
                        this.orderDataAccessMapper.orderToOrderEntity(order)));
    }
}
