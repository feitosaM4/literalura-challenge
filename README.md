# Literalura - Consulta de Livros



> **Descubra milhares de livros com Literalura!** Este projeto consome dados de uma API pública para trazer informações ricas sobre obras literárias. Explore livros, autores, idiomas e muito mais!

---

##  Índice

1. [Configuração do Projeto com Spring Initializr](#-configuração-do-projeto-com-spring-initializr)
2. [Consumo da API Gutendex](#-consumo-da-api-gutendex)
3. [Conversão de Dados](#-conversão-de-dados)
4. [Banco de Dados e Persistência](#-banco-de-dados-e-persistência)
5. [Funcionalidades](#-funcionalidades)
6. [Passo a Passo para Executar o Projeto](#-passo-a-passo-para-executar-o-projeto)
7. [Tecnologias Utilizadas](#-tecnologias-utilizadas)

---

##  Configuração do Projeto com Spring Initializr

### 1. Configurações Iniciais
- **Linguagem:** Java (17 ou superior)
- **Tipo de Projeto:** JAR
- **Ferramenta de Build:** Maven
- **Versão do Spring Boot:** 3.2.3

### 2. Dependências Necessárias
Adicione as seguintes dependências ao seu projeto:
- **Spring Data JPA**
- **PostgreSQL Driver**

### 3. Estrutura Básica de Pastas
- `src/main/java`: Contém o código-fonte principal.
- `src/main/resources`: Contém arquivos de configuração, como `application.properties`.
- `src/test/java`: Contém os testes unitários e de integração.

> **Dica:** Utilize o Spring Initializr para automatizar a criação do projeto com essas dependências.

---

##  Consumo da API Gutendex

A API **Gutendex** é uma fonte pública com informações sobre mais de 70 mil livros do Project Gutenberg. Este projeto utiliza o cliente HTTP nativo do Java para consumir e processar os dados da API.

### Etapas de Integração

1. **Configuração do Cliente HTTP**
   - Utilize a classe `HttpClient` para gerenciar as conexões e realizar as chamadas à API.

2. **Envio de Requisições**
   - Configure requisições HTTP GET com a classe `HttpRequest`.

3. **Processamento de Respostas**
   - Manipule as respostas em formato JSON utilizando `HttpResponse`.

> **Exemplo de Requisição:**
> ```java
> HttpClient client = HttpClient.newHttpClient();
> HttpRequest request = HttpRequest.newBuilder()
>     .uri(URI.create("https://gutendex.com/books/"))
>     .build();
> HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
> System.out.println(response.body());
> ```

---

##  Conversão de Dados

Os dados retornados pela API são convertidos de JSON para objetos Java utilizando a biblioteca Jackson. As seguintes anotações foram usadas:
- `@JsonIgnoreProperties`: Ignora propriedades não utilizadas.
- `@JsonAlias`: Mapeia nomes alternativos de atributos.

> **Dica:** Crie classes DTO (Data Transfer Object) para organizar os dados recebidos e facilitar a manipulação.

---

## Banco de Dados e Persistência

O banco de dados utilizado é o **PostgreSQL**, configurado com o `Spring Data JPA`.

### Estrutura do Banco de Dados
- **Entidades:**
  - `Livro`: Representa os dados de um livro.
  - `Autor`: Representa os dados de um autor.

### Exemplo de Configuração no `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Funcionalidades Implementadas
- Persistência de livros e autores.
- Consulta de autores vivos em determinado ano.
- Filtragem de livros por idioma.

---

##  Funcionalidades

- **Busca por Título:** Encontre livros pelo nome.
- **Filtragem por Idioma:** Liste livros em um idioma específico.
- **Consulta de Autores:** Obtenha informações sobre autores e filtre por ano de nascimento.
- **Listagem Completa:** Exiba todos os livros armazenados no banco de dados.

---

## 🛠 Passo a Passo para Executar o Projeto

1. **Clone o Repositório**
   ```bash
   git clone https://github.com/seu-usuario/literalura-consulta-livros.git
   ```

2. **Abra o Projeto na IDE**
   - Use o IntelliJ IDEA ou outra IDE de sua preferência.

3. **Configure o Banco de Dados**
   - Crie um banco no PostgreSQL e configure o arquivo `application.properties`.

4. **Compile e Execute o Projeto**
   - Rode o arquivo principal `LiteraluraApplication.java`.

5. **Teste as Funcionalidades**
   - Utilize o terminal ou uma ferramenta como Postman para testar as requisições.

---

##  Tecnologias Utilizadas

- **Java JDK:** Versão 17 ou superior  
  [Baixe aqui](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven:** Ferramenta de build 4.0 ou superior
- **Spring Boot:** Versão 3.2.3  
  [Spring Initializr](https://start.spring.io/)
- **PostgreSQL:** Banco de dados relacional (versão 16 ou superior)  
  [Download PostgreSQL](https://www.postgresql.org/download/)
- **IDE IntelliJ IDEA (opcional):** Ambiente de desenvolvimento integrado  
  [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

