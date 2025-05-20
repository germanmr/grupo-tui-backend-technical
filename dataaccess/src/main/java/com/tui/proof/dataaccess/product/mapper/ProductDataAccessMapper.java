package com.tui.proof.dataaccess.product.mapper;

import com.tui.proof.dataaccess.product.entity.ProductEntity;
import com.tui.proof.domain.entity.Product;
import com.tui.proof.domain.valueobject.MonetaryAmount;
import com.tui.proof.domain.valueobject.Name;
import com.tui.proof.domain.valueobject.ProductId;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Mapper
public interface ProductDataAccessMapper {

    default Optional<Product> optionalProductEntityToOptionalProduct(final Optional<ProductEntity> productEntity) {
        return productEntity.map(pe ->
                Product.builder()
                        .productId(ProductId.builder().value(pe.getId()).build())
                        .name(Name.builder().value(pe.getName()).build())
                        .price(MonetaryAmount.builder().value(pe.getPrice()).build())
                        .build());
    }
}
