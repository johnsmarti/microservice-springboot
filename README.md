# Plataforma de Metodologias e Testes para Fábrica de Pneus

Este repositório contém um exemplo de arquitetura de microsserviços em Java 21/Spring Boot para gerenciar
metodologias de fabricação e testes de qualidade aplicados a lotes de pneus.

## Estrutura

- `methodology-service`: API responsável por cadastrar e revisar metodologias de processo (laboratório, cura, inspeção etc.).
- `testing-service`: API dedicada ao registro de testes executados contra os lotes de pneus e suas metodologias associadas.

Cada serviço expõe endpoints REST (`/api/methodologies` e `/api/tests`) com validação, tratamento de erros via
`ProblemDetail` e armazenamento in-memory para facilitar o desenvolvimento inicial.

## Pré-requisitos

- Java 21+ (funciona em Java 25 assim que disponível).
- Maven 3.9+

## Como executar

Em terminais separados execute:

```bash
mvn -pl methodology-service spring-boot:run
mvn -pl testing-service spring-boot:run
```

Os serviços serão expostos nas portas `8081` e `8082` respectivamente.

## Exemplos de chamadas

### Criar metodologia

```bash
curl -X POST http://localhost:8081/api/methodologies \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Balanceamento Dinâmico",
    "description": "Sequência de balanceamento em alta rotação",
    "processArea": "Laboratório",
    "expectedDurationHours": 6
  }'
```

### Registrar teste

```bash
curl -X POST http://localhost:8082/api/tests \
  -H 'Content-Type: application/json' \
  -d '{
    "batchCode": "LOTE-2024-05-A",
    "methodologyId": "<ID retornado pelo serviço de metodologias>",
    "stage": "Vulcanização",
    "status": "EM_EXECUCAO",
    "summary": "Rodagem a 140km/h"
  }'
```

## Testes automatizados

Execute a suíte com:

```bash
mvn test
```

Isso garante as regras básicas de negócio dos serviços utilizando repositórios em memória.
