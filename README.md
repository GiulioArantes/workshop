# Workshop

![workshop](https://i.postimg.cc/VNKBF4cV/shop.webp)

O **Workshop** é uma API completa e intuitiva para o gerenciamento de um comércio, construída com **Spring Boot**. Quando digo que é completa, realmente é, contamos com categorias, ordem, item da ordem, pagamento, produto e usuário, tudo relacionado de uma forma em que 
a organização do comércio fica simples e completa, sem diversos endpoints desnecessários.

>🔧 **Observação:** O projeto está completo, porém, ele é local mas nada impede de você subir em algum lugar como o **Render**, **AWS** ou o que for da sua preferência.

## ✨ Funcionalidades

* 🔗 **API RESTful completa**, seguindo boas práticas de desenvolvimento.
* **🔎 CRUD de Usuários, Ordens, Produtos e Categorias:**
    * Cadastrar, listar, editar e excluir.
    * Buscar por todos ou ID.

*  🏗️ **Arquitetura organizada:**
    * **Teste:** Camada de teste, aonde é possível inserir os dados que serão usados como base no banco de dados H2, no objetivo de facilitar o desenvolvimento.   
    * **Model:** Estrutura das entidades (**Usuário**, **Ordem**, **Categoria**, **Item da Ordem**, **Pagamento**, **Produto**).
    * **Repository:** Comunicação com o banco de dados.
    * **Service:** Regra de negócio e tratamento de erros.
    * **Exceptions Handlers:** Tratamento de erros personalizado para melhor idêntificação do problema.
    * **Controller:** Disponibilização dos endpoints para consumo externo.
*  🚀 **Escalável:** Arquitetura pensada para facilitar o crescimento do projeto e a inclusão de novas funcionalidades no futuro.
> 💡 Este projeto vai além de um MVP e está em constante evolução para atender a demandas mais robustas no gerenciamento de conteúdo.

## 🚀 Tecnologias Utilizadas

Este projeto foi construído com uma stack moderna, performática e escalável:

* **Backend & Banco de Dados:**
    * [**Spring Boot**](https://spring.io/projects/spring-boot) - Framework Java para aplicações web robustas e seguras.
    * [**PostgreSQL**](https://www.postgresql.org/) - Banco de dados relacional que pode ser utilizado no ambiente de **produção**.
    * [**H2**](https://www.mysql.com/) - Banco de dados relacional utilizado no ambiente de **desenvolvimento**, simples e prático para testes.
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

> **⚠ Observação:** O Front-end desse projeto será implementado logo, fique atento!

### 🔗 Endpoints

> ✅ Supondo que a aplicação está rodando na porta padrão `localhost:8080` (ajuste conforme necessário).

**GET**
* `/users` — Lista todos os usuários  
* `/categories` — Lista todas as categorias
* `/orders` — Lista todas as ordens
* `/products` — Lista todos os produtos
* `/usuarios/{id}` — Busca um usuario por ID
* `/categories/{id}` — Busca uma categoria por ID
* `/orders/{id}` — Busca uma ordem por ID
* `/users/{id}` — Busca um usuário por ID

**POST & PUT**
* `/users` — Cria ou atualiza um usuário

**DELETE**
* `/users/{id}` — Deleta um usuário

### 🗂️ Atributos das entidades

🔹**Usuario**

| Atributo        | Tipo              | Descrição                                      |
| --------------- | ----------------- | ---------------------------------------------- |
| `id`            | Long              | Identificador único (gerado automaticamente)   |
| `name`          | String            | Nome do usuário                                |
| `email`         | String            | E-mail do usuário                              |
| `phone`         | String            | Celular do usuário                             |
| `password`      | String            | Senha do usuário                               |
| `orders`        | List\<Order>      | Lista de ordens associadas (um para muitos)    |

🔹**Order**

| Atributo        | Tipo              | Descrição                                            |
| --------------- | ----------------- | ---------------------------------------------------- |
| `id`            | Long              | Identificador único (gerado automaticamente)         |
| `moment`        | Instant           | Momento em que foi criado a ordem                    |
| `orderStatus`   | Integer           | Status em que a ordem se encontra                    |
| `client`        | User              | cliente relacionado a ordem (muitos para um)         |
| `items`         | Set\<OrderItem>   | Lista de itens associados (um para muitos)           |
| `payment`       | Payment           | Pagamento a realizar (um para um)                    |

🔹**OrderItem**

| Atributo        | Tipo              | Descrição                                            |
| --------------- | ----------------- | ---------------------------------------------------- |
| `id`            | OrderItemPK       | Explicar brevemente sobre                            |
| `quantity`      | Integer           | Quantidade de itens                                  |
| `price`         | Integer           | Status em que a ordem se encontra                    |
| `client`        | User              | cliente relacionado a ordem (muitos para um)         |
| `items`         | Set\<OrderItem>   | Lista de itens associados (um para muitos)           |
| `payment`       | Payment           | Pagamento a realizar (um para um)                    |

🔸 **Tema**

| Atributo     | Tipo            | Descrição                                      |
| ------------ | --------------- | ---------------------------------------------- |
| `id`         | Long            | Identificador único (gerado automaticamente)   |
| `descricao`  | String          | Descrição do tema                              |
| `postagem`   | List\<Postagem> | Lista de postagens associadas (um para muitos) |

✅ **Observação:**
* A entidade **Postagem** está associada a **um único Tema** e **um único Usuário**.
* As entidades **Tema** e **Usuario** podem ter várias **Postagens** vinculadas.
* O campo `orderStatus` na entidade **Order** está vinculado a um Enum que recebe o número inteiro e encontra o status correspondente.
  * 1 - WAITING_PATMENT, 2 - PAID, 3 - SHIPPED, 4 - DELIVERED, 5 - CANCELED.
* Fazer a descrição do que significa esse id do OrderItem.
* O campo `usuario` na entidade **Usuario** representa o **e-mail** utilizado para autenticação.
* O campo `data` em **Postagem** registra automaticamente o momento da criação.

## 🤝 Contribuição

Sua colaboração é muito bem-vinda! Existem duas formas de contribuir:

* **💡 Sugestões e feedbacks:** Me envie um e-mail em [giulio.arantes@icloud.com](giulio.arantes@icloud.com) ou me chame no [LinkedIn](https://www.linkedin.com/in/giulio-arantes/).
* **🔧 Contribuição no código:** Fork o projeto, crie suas melhorias e envie um Pull Request.

> Toda contribuição será analisada com atenção e respeito. Vamos construir algo incrível juntos! 💙
