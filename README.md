## Sistema de Gerenciamento de Usuários, Tarefas, Autores e Livros

Este sistema foi desenvolvido como parte de um projeto acadêmico, com o objetivo de gerenciar usuários, tarefas, autores e livros em uma plataforma utilizando Java, Spring Boot e MongoDB.
Funcionalidades principais:

    Gerenciamento de Usuários:
        Permite criar, ler, atualizar e excluir usuários.
        Cada usuário possui informações como nome, e-mail, telefone e uma lista de tarefas associadas.

    Gerenciamento de Tarefas:
        Criação, leitura, atualização e exclusão de tarefas.
        Tarefas estão associadas aos usuários e possuem atributos como título, descrição, prioridade e status de conclusão.
        As tarefas podem ser marcadas como concluídas.

    Gerenciamento de Autores:
        Permite criar, ler, atualizar e excluir autores.
        Cada autor possui informações como nome, biografia e uma lista de livros escritos.

    Gerenciamento de Livros:
        Criação, leitura, atualização e exclusão de livros.
        Cada livro possui título, descrição e um autor associado.
        Livros podem ser associados a autores durante o processo de criação e atualização.

    Associação de Tarefas aos Usuários:
        Usuários podem ter várias tarefas associadas.
        Tarefas podem ser adicionadas a um usuário e listadas junto com as demais tarefas do usuário.

    Armazenamento em MongoDB:
        Todas as informações de usuários, tarefas, autores e livros são armazenadas em um banco de dados MongoDB.
        O banco de dados utiliza uma estrutura de documentos para persistir os dados.

    API Restful:
        A aplicação expõe uma API RESTful que permite realizar operações de CRUD (Criar, Ler, Atualizar, Excluir) para usuários, tarefas, autores e livros.
        A API também oferece funcionalidade para listar todos os usuários, tarefas, autores e livros com suporte a paginação.

Tecnologias utilizadas:

    Java 17
    Spring Boot (para desenvolvimento da API e gerenciamento da lógica de negócio)
    MongoDB (para persistência de dados)
    Swagger (para documentação da API)
    Postman (para testes das funcionalidades da API)

Testes:

A API foi testada utilizando o Postman e todas as operações de CRUD foram verificadas. As funcionalidades testadas incluem:

    Cadastro de Usuários e Tarefas
    Associação de Tarefas a Usuários
    Gerenciamento de Autores e Livros
    Listagem de Tarefas e Livros associados a usuários e autores
