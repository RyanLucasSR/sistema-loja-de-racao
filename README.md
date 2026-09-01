# 🐶 Sistema Loja de Ração

Sistema desenvolvido em Java para auxiliar no gerenciamento de produtos de uma loja de ração da minha família.

O projeto surgiu a partir de uma necessidade real: organizar melhor os produtos, facilitar a consulta das informações durante o atendimento e auxiliar na colocação de preços nos produtos.

## 🚀 Sobre o projeto

Este projeto está sendo desenvolvido de forma incremental. A primeira versão foi construída utilizando uma interface de console, permitindo focar inicialmente na lógica da aplicação, programação orientada a objetos e persistência dos dados.

Atualmente, o sistema permite cadastrar e consultar produtos e rações utilizando Java, JPA/Hibernate e MySQL.

## ⚙️ Funcionalidades atuais

- [x] Cadastro de produtos
- [x] Cadastro de rações
- [x] Listagem de produtos
- [x] Consulta de produtos por nome
- [x] Consulta de rações por peso
- [x] Persistência dos dados no banco de dados
- [x] Validação de dados
- [x] Tratamento de exceções
- [x] Interface via console

## 🛠️ Tecnologias utilizadas

- **Java**
- **JPA**
- **Hibernate**
- **MySQL**
- **JPQL**
- **Maven**
- **IntelliJ IDEA**

## 🧠 Conceitos aplicados

Durante o desenvolvimento foram utilizados conceitos como:

- Programação Orientada a Objetos (POO)
- Encapsulamento
- Herança
- Generics
- Classes e métodos
- Exceções personalizadas
- DAO (Data Access Object)
- Persistência de dados
- Transações
- Consultas com JPQL
- Mapeamento de entidades com JPA
- Herança de entidades com `@Inheritance`

## 🗂️ Estrutura do projeto

```text
src
└── br.com.ryanlucas.sistemalojaderacao
    ├── DAO
    │   ├── DAO
    │   ├── ProdutoDAO
    │   └── RacaoDAO
    │
    ├── excecao
    │   ├── CampoObrigatorioException
    │   └── InvalidoException
    │
    ├── model
    │   ├── GerenciadorDeProduto
    │   ├── Produto
    │   └── ProdutoRacao
    │
    ├── teste
    │   └── Teste
    │
    └── view
        ├── AplicacaoConsole
        └── Menu