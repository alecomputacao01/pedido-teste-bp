package br.com.algar.poc.pedido.domain.ports.in;

import br.com.algar.poc.pedido.domain.model.Product;

import java.util.List;

/** Porta de entrada (driving port) — lista todos os produtos cadastrados. */
public interface ListProductsUseCase {

    List<Product> listAll();
}