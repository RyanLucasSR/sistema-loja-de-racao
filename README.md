🐶 Sistema Loja de Ração

Sistema desenvolvido em Java para auxiliar no gerenciamento de produtos de uma loja de ração da minha família.

O projeto surgiu a partir de uma necessidade real: organizar melhor os produtos, facilitar a consulta das 
informações durante o atendimento e auxiliar no gerenciamento dos produtos da loja.

🚀 Sobre o projeto

O projeto foi desenvolvido de forma incremental.

A primeira versão foi construída utilizando uma interface de console, permitindo focar inicialmente na lógica
da aplicação, Programação Orientada a Objetos e persistência dos dados.

Posteriormente, o sistema evoluiu para uma aplicação com interface gráfica utilizando JavaFX, mantendo a 
estrutura de persistência com JPA/Hibernate e MySQL.

Atualmente, o sistema permite cadastrar, listar, consultar, editar e excluir produtos e rações através da 
interface gráfica.

⚙️ Funcionalidades
Produtos

Cadastro de produtos

Listagem de produtos

Consulta de produtos por nome

Edição de produtos

Exclusão de produtos

Confirmação antes da exclusão

Rações

Cadastro de rações

Listagem de rações

Consulta de rações por peso

Edição de rações

Exclusão de rações

Confirmação antes da exclusão

Sistema

Persistência dos dados no banco de dados

Validação de dados

Tratamento de exceções

Interface gráfica com JavaFX

Navegação entre telas

Botões de salvar, voltar e sair

🛠️ Tecnologias utilizadas
Java 21
JavaFX
JPA
Hibernate
MySQL
JPQL
Git/GitHub
IntelliJ IDEA
🧠 Conceitos aplicados

Durante o desenvolvimento foram utilizados conceitos como:

Programação Orientada a Objetos (POO)
Encapsulamento
Herança
Generics
Classes e métodos
Exceções personalizadas
DAO (Data Access Object)
Persistência de dados
Transações
Consultas com JPQL
Mapeamento de entidades com JPA
Herança de entidades com @Inheritance
Integração entre interface gráfica e camada de persistência
🖥️ Interface

A aplicação utiliza JavaFX para fornecer uma interface gráfica para as operações do sistema, substituindo a 
interface de console utilizada na primeira versão do projeto.

📈 Evolução do projeto

O projeto começou como uma aplicação de console, com foco na construção da lógica e da camada de persistência.

Com a evolução do desenvolvimento, foi adicionada uma interface gráfica utilizando JavaFX, permitindo que as 
operações do sistema fossem realizadas de maneira mais prática.

A estrutura foi sendo desenvolvida e testada incrementalmente, conectando a interface gráfica ao back-end e 
ao banco de dados.

🗂️ Estrutura do projeto
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
│
└── view
├── Aplicacao
├── MenuGeral
├── MenuProduto
├── MenuRacao
├── MenuListarProduto
├── MenuListarRacao
└── ...
📚 Objetivo

Além de resolver uma necessidade real da loja, o projeto está sendo utilizado como forma de colocar em prática 
conceitos de desenvolvimento Java, persistência de dados, banco de dados e desenvolvimento de interfaces 
gráficas.

O sistema continua em desenvolvimento e poderá receber novas funcionalidades conforme surgirem necessidades 
durante o uso.