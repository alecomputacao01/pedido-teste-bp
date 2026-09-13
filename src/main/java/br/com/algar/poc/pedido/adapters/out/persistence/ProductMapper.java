package br.com.algar.poc.pedido.adapters.out.persistence;

import br.com.algar.poc.pedido.domain.model.Product;
import br.com.algar.poc.pedido.domain.model.ProductId;
import br.com.algar.poc.pedido.domain.model.Sku;

final class ProductMapper {

    private ProductMapper() {
    }

    static ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(
                product.id().value(),
                product.sku().value(),
                product.name(),
                product.price());
    }

    static Product toDomain(ProductJpaEntity entity) {
        return Product.register(
                ProductId.of(entity.getId()),
                Sku.of(entity.getSku()),
                entity.getName(),
                entity.getPrice());
    }
}
