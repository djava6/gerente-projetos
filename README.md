# Gerente de Projetos

API REST desenvolvida em Java 17 com Spring Boot e Maven para gerenciamento de projetos.

## Como executar

```bash
mvn clean package
mvn spring-boot:run
```
Acesse em: [http://localhost:8080](http://localhost:8080)

## Endpoints principais

### Projetos
- GET /projetos
- GET /projetos/{id}
- POST /projetos
- PUT /projetos/{id}
- DELETE /projetos/{id}

### Responsáveis
- GET /responsaveis
- GET /responsaveis/{id}
- POST /responsaveis
- PUT /responsaveis/{id}
- DELETE /responsaveis/{id}

Banco em memória H2 disponível em `/h2-console`.

---
Feito com ❤️ por [Carlos Daniel de Mattos Mercer] (djava2@gmail.com)
