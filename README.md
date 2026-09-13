# pedido-teste-bp

Serviço gerado pela PoC Backstage + ArchUnit

Gerado pelo Software Template `poc-java-arquitetura-ddd` do Backstage (PoC `validacao-archunit`).

**Combinação:** framework=`spring-boot-4` · banco=`postgresql` · abordagem=`hexagonal`

**Pacote Java:** `br.com.algar.poc.pedido`

> O código gerado inclui um domínio de exemplo (agregado `Product`, Value Object `Sku`) só para
> demonstrar a abordagem arquitetural em funcionamento de ponta a ponta. Troque essas classes pelas
> entidades reais do seu domínio antes do primeiro caso de uso — a suíte ArchUnit valida a
> *estrutura* de pacotes (`domain`/`application`/`adapters`, ou equivalente da abordagem escolhida),
> não os nomes das classes dentro delas, então renomear/remover `Product`/`Sku` não quebra a
> validação arquitetural.

## Estrutura

- `domain/` — Aggregate, Value Objects e portas (in/out) — sem dependência de framework.
- `application/` — casos de uso, orquestra o domínio via portas.
- `adapters/in/web/` — controllers/resources REST.
- `adapters/out/persistence/` — persistência, migrations Flyway.
- `arch/HexagonalArchitectureTest.java` — regras ArchUnit que fiscalizam as camadas acima.

## Rodar localmente

```bash
mvn test               # unitários + application + ArchUnit
mvn test -Dtest='*IT'  # integração (Testcontainers — requer Docker)
mvn spring-boot:run
```

O pipeline `.github/workflows/build-and-archunit.yml` roda os três grupos de teste automaticamente em
todo push/PR para `main`.
