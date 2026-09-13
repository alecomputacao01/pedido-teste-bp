package br.com.algar.poc.pedido.adapters.in.web.dto;

import br.com.algar.poc.pedido.domain.model.Product;

import java.math.BigDecimal;

public record ProductResponse(String id, String sku, String name, BigDecimal price) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.id().toString(),
                product.sku().value(),
                product.name(),
                product.price());
    }
}
