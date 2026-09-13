package br.com.algar.poc.pedido.application.usecase;

import br.com.algar.poc.pedido.domain.model.Product;
import br.com.algar.poc.pedido.domain.model.ProductId;
import br.com.algar.poc.pedido.domain.model.Sku;
import br.com.algar.poc.pedido.domain.ports.out.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListProductsServiceTest {

    @Mock
    private ProductRepository repository;

    @Test
    void deveListarTodosOsProdutosCadastrados() {
        var product = Product.register(ProductId.newId(), Sku.of("ABC-1234"), "Notebook", new BigDecimal("100.00"));
        when(repository.findAll()).thenReturn(List.of(product));

        var service = new ListProductsService(repository);
        var products = service.listAll();

        assertThat(products).containsExactly(product);
    }
}
