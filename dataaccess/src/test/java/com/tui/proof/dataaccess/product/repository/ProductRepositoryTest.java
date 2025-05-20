package com.tui.proof.dataaccess.product.repository;

import com.tui.proof.dataaccess.OrderData;
import com.tui.proof.dataaccess.OrderDataAccessData;
import com.tui.proof.dataaccess.config.DataAccessTestConfiguration;
import com.tui.proof.dataaccess.product.adapter.ProductRepositoryImpl;
import com.tui.proof.dataaccess.product.entity.ProductEntity;
import com.tui.proof.dataaccess.product.mapper.ProductDataAccessMapper;
import com.tui.proof.dataaccess.product.mapper.ProductDataAccessMapperImpl;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.ProductId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {
        DataAccessTestConfiguration.class,
        ProductDataAccessMapperImpl.class})
class ProductRepositoryTest {

    private final Long productIdValue = 1L;
    private final ProductId productId = ProductId.builder().value(productIdValue).build();
    private final Product product = OrderData.anyProduct();

    private final ProductEntity productEntity = OrderDataAccessData.anyProductEntity();

    @Autowired
    private ProductJpaRepository productJpaRepository;
    @Autowired
    private ProductDataAccessMapper productDataAccessMapper;

    private ProductRepositoryImpl productRepository;

    @BeforeAll
    void setUp() {
        this.productRepository = new ProductRepositoryImpl(
                productJpaRepository, productDataAccessMapper);
    }

    @Test
    void can_get_by_id() {
        when(productJpaRepository.findById(productIdValue)).thenReturn(Optional.of(productEntity));
        assertEquals(Optional.of(product), productRepository.getProductById(productId));
    }

    @Test
    void can_get_by_id_no_product() {
        when(productJpaRepository.findById(productIdValue)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), productRepository.getProductById(productId));
    }
}