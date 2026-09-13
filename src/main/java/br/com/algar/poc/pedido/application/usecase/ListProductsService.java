package br.com.algar.poc.pedido.application.usecase;

import br.com.algar.poc.pedido.domain.model.Product;
import br.com.algar.poc.pedido.domain.ports.in.ListProductsUseCase;
import br.com.algar.poc.pedido.domain.ports.out.ProductRepository;

import java.util.List;

public class ListProductsService implements ListProductsUseCase {

    private final ProductRepository repository;

    public ListProductsService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> listAll() {
        return repository.findAll();
    }
}
