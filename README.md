# Workshop

![workshop](https://i.postimg.cc/VNKBF4cV/shop.webp)

O **Workshop** é uma API RESTful robusta e intuitiva para o gerenciamento de um comércio, construída com **Spring Boot**. Ela foi pensada para oferecer uma estrutura enxuta, mas completa, com as principais entidades de um sistema comercial: usuários, produtos, pedidos, categorias, pagamentos e itens de pedido — tudo isso de forma bem organizada, evitando endpoints redundantes.

>🔧 **Observação:** O projeto está totalmente funcional em ambiente local, mas pode ser facilmente implantado em plataformas como **Render**, **AWS**, entre outras.

## ✨ Funcionalidades

* 🔗 **API RESTful completa**, seguindo boas práticas de desenvolvimento.
* **🔎 CRUD de Usuários, Ordens, Produtos e Categorias:**
    * Cadastrar, listar, editar e excluir.
    * Buscar todos ou por ID.

*  🏗️ **Arquitetura bem definida:**
    * **Testes:** Inserção de dados pré-definidos no banco H2 para facilitar o desenvolvimento.  
    * **Model:** Estrutura de entidades principais (Usuário, Ordem, Produto, etc.).
    * **Repository:** Interface com o banco de dados.
    * **Service:** Camada de regras de negócio e validações.
    * **Exception Handlers:** Tratamento de erros personalizado.
    * **Controller:** Exposição dos endpoints para consumo externo.
*  🚀 **Escalável:** Projeto preparado para crescer com facilidade e receber novas funcionalidades.

> 💡 Este projeto vai além de um MVP e está em constante evolução para atender demandas mais complexas no gerenciamento de dados comerciais.

## 🚀 Tecnologias Utilizadas

Este projeto foi construído com uma stack moderna, performática e escalável:

* **Backend & Banco de Dados:**
    * [**Spring Boot**](https://spring.io/projects/spring-boot) - Framework Java para aplicações web robustas e seguras.
    * [**PostgreSQL**](https://www.postgresql.org/) - Banco de dados relacional que pode ser utilizado no ambiente de **produção**.
    * [**H2**](https://www.h2database.com/html/main.html) - Banco de dados relacional utilizado no ambiente de **desenvolvimento**, simples e prático para testes.
* **Testes:**
    * [**Postman**](https://www.postman.com/) - Ferramenta para teste e validação de APIs.

## ⚙️ Como Rodar o Projeto

### 🖥️ Aplicação Back-end

Siga os passos abaixo para executar o backend localmente:

```bash
# 1. Clone o repositório
git clone https://github.com/GiulioArantes/workshop.git

# 2. Navegue até o diretório do projeto
cd workshop

# 3. Configure o arquivo application.properties em:
src/main/resources/application.properties

# 4. Defina o perfil de teste
spring.profiles.active=test

# 5. Execute a aplicação via sua IDE ou pelo terminal
```

### 🌐 Front-end

> ⚠ **Em desenvolvimento**: O front-end será disponibilizado futuramente.

### 🔗 Endpoints

> Supondo que a aplicação esteja rodando em `localhost:8080`

**GET**
* `/users` — Lista todos os usuários
* `/users/{id}` — Busca um usuário por ID
* `/orders` — Lista todas as ordens
* `/orders/{id}` — Busca uma ordem por ID
* `/products` — Lista todos os produtos
* `/products/{id}` — Busca um produto por ID
* `/categories` — Lista todas as categorias
* `/categories/{id}` — Busca uma categoria por ID

**POST & PUT**
* `/users` — Cria ou atualiza um usuário

**DELETE**
* `/users/{id}` — Remove um usuário

### 🗂️ Atributos das entidades

🔹**User**

Representa o usuário do sistema, normalmente um cliente.
| Atributo   | Tipo         | Descrição                                    |
| ---------- | ------------ | -------------------------------------------- |
| `id`       | Long         | Identificador único do usuário (autogerado). |
| `name`     | String       | Nome do usuário.                             |
| `email`    | String       | E-mail de contato.                           |
| `phone`    | String       | Telefone para contato.                       |
| `password` | String       | Senha de acesso.                             |
| `orders`   | List\<Order> | Lista de pedidos feitos pelo usuário (1\:N). |

🔁 **Relacionamento:**
Um usuário pode ter várias ordens associadas a ele (relacionamento **um-para-muitos**).

---
🔹**Order**

Representa um pedido realizado por um usuário.
| Atributo      | Tipo               | Descrição                                                |
| ------------- | ------------------ | -------------------------------------------------------- |
| `id`          | Long               | Identificador do pedido (autogerado).                    |
| `moment`      | Instant            | Data e hora em que o pedido foi criado.                  |
| `orderStatus` | Enum (OrderStatus) | Status atual do pedido (usando enum com código interno). |
| `client`      | User               | Usuário que realizou o pedido (N:1).                     |
| `items`       | Set\<OrderItem>    | Itens contidos no pedido (1\:N).                         |
| `payment`     | Payment            | Pagamento associado ao pedido (1:1).                     |

✅ **Método especial:**
`getTotal()` retorna o valor total da ordem somando os subtotais dos itens.

📌 **Enum** `OrderStatus`:
Enum que representa o status do pedido, com os seguintes valores:

* `1` – WAITING_PAYMENT
* `2` – PAID
* `3` – SHIPPED
* `4` – DELIVERED
* `5` – CANCELED

---
🔸**OrderItem**

Representa um item de um pedido (produto + quantidade + preço). É uma **entidade de associação** entre `Order` e `Product`, utilizando **chave composta**.
| Atributo   | Tipo        | Descrição                                        |
| ---------- | ----------- | ------------------------------------------------ |
| `id`       | OrderItemPK | Chave composta com `order` e `product`.          |
| `order`    | Order       | Pedido ao qual o item pertence (via `id.order`). |
| `product`  | Product     | Produto vinculado ao item (via `id.product`).    |
| `quantity` | Integer     | Quantidade de unidades do produto no pedido.     |
| `price`    | Double      | Preço unitário do produto no momento da compra.  |

✅ **Método especial**:
`getSubTotal()` retorna o valor total do item (`price * quantity`).

📌 **Chave composta**:
A classe `OrderItemPK` é usada para compor a chave com `Order` e `Product`. A anotação `@Embeddable` permite que essa composição ocorra de forma transparente no banco de dados.

---
🔹 **Product**

Representa um produto disponível para venda.
| Atributo      | Tipo            | Descrição                                         |
| ------------- | --------------- | ------------------------------------------------- |
| `id`          | Long            | Identificador do produto (autogerado).            |
| `name`        | String          | Nome do produto.                                  |
| `description` | String          | Descrição detalhada.                              |
| `price`       | Double          | Preço unitário.                                   |
| `imgUrl`      | String          | URL da imagem ilustrativa.                        |
| `categories`  | Set\<Category>  | Categorias às quais o produto pertence (N\:N).    |
| `items`       | Set\<OrderItem> | Itens de pedidos em que o produto aparece (1\:N). |

✅ **Método especial**:
`getOrders()` retorna os pedidos nos quais o produto foi incluído, com base nos `OrderItems`.

---
🔹 **Category**

Representa uma categoria de produtos (como "Eletrônicos", "Roupas", etc).
| Atributo   | Tipo          | Descrição                                      |
| ---------- | ------------- | ---------------------------------------------- |
| `id`       | Long          | Identificador único da categoria (autogerado). |
| `name`     | String        | Nome da categoria.                             |
| `products` | Set\<Product> | Produtos associados a esta categoria (N\:N).   |

🔁 **Relacionamento**:
Categoria e Produto possuem relação **muitos-para-muitos**, mapeada por uma tabela intermediária `tb_product_category`

---
🔸 **Payment**

Representa o pagamento de uma ordem.
| Atributo | Tipo    | Descrição                                        |
| -------- | ------- | ------------------------------------------------ |
| `id`     | Long    | Identificador do pagamento (autogerado).         |
| `moment` | Instant | Data e hora em que o pagamento foi efetuado.     |
| `order`  | Order   | Pedido ao qual o pagamento está vinculado (1:1). |

🧠 **Mapeamento especial:**
A anotação `@MapsId` garante que o pagamento use o mesmo ID da ordem, caracterizando um relacionamento **um-para-um com chave compartilhada**.

## 🤝 Contribuição

Contribuições são sempre bem-vindas! Você pode ajudar de duas formas:

* **💡 Sugestões e feedbacks:** Entre em contato por e-mail em [giulio.arantes@icloud.com](giulio.arantes@icloud.com) ou no [LinkedIn](https://www.linkedin.com/in/giulio-arantes/).
* **🔧 Contribuição com código:** Fork o projeto, adicione suas melhorias e envie um Pull Request.

> Toda contribuição será bem avaliada e valorizada. Vamos construir algo incrível juntos! 💙
