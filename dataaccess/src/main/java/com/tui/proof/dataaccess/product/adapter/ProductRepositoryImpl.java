package com.tui.proof.dataaccess.product.adapter;

import com.tui.proof.dataaccess.product.mapper.ProductDataAccessMapper;
import com.tui.proof.dataaccess.product.repository.ProductJpaRepository;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.ports.output.repository.ProductRepository;
import com.tui.proof.domain.valueobject.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductDataAccessMapper productDataAccessMapper;

    @Autowired
    public ProductRepositoryImpl(final ProductJpaRepository productJpaRepository,
                                 final ProductDataAccessMapper productDataAccessMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productDataAccessMapper = productDataAccessMapper;
    }


    @Override
    public Optional<Product> getProductById(final ProductId productId) {
        return this.productDataAccessMapper.optionalProductEntityToOptionalProduct(
                this.productJpaRepository.findById(productId.getValue()));
    }
}
