package br.com.algar.poc.pedido.config;

import br.com.algar.poc.pedido.application.usecase.ListProductsService;
import br.com.algar.poc.pedido.application.usecase.RegisterProductService;
import br.com.algar.poc.pedido.domain.ports.in.ListProductsUseCase;
import br.com.algar.poc.pedido.domain.ports.in.RegisterProductUseCase;
import br.com.algar.poc.pedido.domain.ports.out.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Liga a porta de entrada à sua implementação. A classe application.usecase.RegisterProductService
 * permanece livre de anotações Spring (@Service etc.) de propósito — a fiação explícita mantém a
 * camada de aplicação portável e testável sem contexto, como já é feito em RegisterProductServiceTest.
 */
@Configuration
public class BeanConfig {

    @Bean
    public RegisterProductUseCase registerProductUseCase(ProductRepository productRepository) {
        return new RegisterProductService(productRepository);
    }

    @Bean
    public ListProductsUseCase listProductsUseCase(ProductRepository productRepository) {
        return new ListProductsService(productRepository);
    }
}
