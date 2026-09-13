package br.com.algar.poc.pedido.adapters.in.web;

import br.com.algar.poc.pedido.adapters.in.web.dto.ProductRequest;
import br.com.algar.poc.pedido.adapters.in.web.dto.ProductResponse;
import br.com.algar.poc.pedido.domain.ports.in.ListProductsUseCase;
import br.com.algar.poc.pedido.domain.ports.in.RegisterProductUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Adapter de entrada (driving adapter) — só orquestra, não contém regra de negócio
 * (fiscalizado indiretamente por HexagonalArchitectureTest, já que qualquer regra nova
 * teria que morar em domain/application para não violar as demais dependências permitidas).
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final RegisterProductUseCase registerProductUseCase;
    private final ListProductsUseCase listProductsUseCase;

    public ProductController(RegisterProductUseCase registerProductUseCase, ListProductsUseCase listProductsUseCase) {
        this.registerProductUseCase = registerProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> register(@Valid @RequestBody ProductRequest request) {
        var product = registerProductUseCase.register(request.sku(), request.name(), request.price());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.from(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listAll() {
        var products = listProductsUseCase.listAll().stream()
                .map(ProductResponse::from)
                .toList();
        return ResponseEntity.ok(products);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleConflict() {
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequest() {
    }
}
