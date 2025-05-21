
# 🛠️ Motix - API de Usuários

Este projeto faz parte do ecossistema da solução **Motix**, voltada para controle e rastreamento de motocicletas em pátios via IoT. Este serviço é responsável pelo **gerenciamento de usuários**, incluindo operações de CRUD com suporte a Swagger para documentação da API.

---

## 📦 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **OpenAPI (Swagger)**
- **PostgreSQL (ou outro banco relacional)**
- **Lombok**
- **Maven**

---

## 🔁 Funcionalidades da API

A API permite:

- 🔍 Listar todos os usuários
- 🔍 Buscar usuário por:
  - `ID` (UUID)
  - `RM` (registro)
  - `Nome`
- ➕ Cadastrar um novo usuário
- ✏️ Atualizar um usuário existente
- ❌ Remover usuário por:
  - `ID`
  - `RM`

---

## 🔓 Exemplos de Endpoints - Usuarios

| Método | Endpoint                      | Descrição                       |
|--------|-------------------------------|----------------------------------|
| GET    | `/users`                      | Lista todos os usuários          |
| GET    | `/users/{id}`                 | Busca usuário por ID             |
| GET    | `/users/rm/{rm}`              | Busca usuário por RM             |
| GET    | `/users/{name}`               | Busca usuários por nome          |
| POST   | `/users`                      | Cadastra um novo usuário         |
| PUT    | `/users`                      | Atualiza um usuário              |
| DELETE | `/users/{id}`                 | Remove usuário por ID            |
| DELETE | `/users/rm/{rm}`              | Remove usuário por RM            |

> Todas as rotas estão documentadas no Swagger UI.

---

## 📄 Documentação Swagger

A documentação interativa estará disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- Java 17+
- Maven
- Banco de dados PostgreSQL (ou compatível)

### Rodando localmente

```bash
# Clone o repositório
git clone https://github.com/klebers022/motix.git
cd motix
cd java

# Compilar e rodar
./mvnw spring-boot:run
```

---

## 🧑‍💻 Participantes

- **Kleber da Silva** — RM: 557887
- **Nicolas Barutti** — RM: 554944
- **Lucas Rainha** — RM: 558471

---

## 📁 Estrutura do Projeto

```
src/
├── controllers/        # Camada REST (UserController)
├── services/           # Camada de negócio (UserService)
├── models/             # Entidades e DTOs
└── repositories/       # Interface de persistência
```

---

## 📬 Contato

Em caso de dúvidas, entre em contato via [email institucional] ou [GitHub Issues].

---
