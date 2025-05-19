package com.tui.proof.domain.ports.output.repository;

import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.ProductId;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> getProductById(final ProductId productId);
}