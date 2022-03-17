[![author](https://img.shields.io/badge/author-robson_silva-red.svg)](https://www.linkedin.com/in/robson-silva-0b79291b6)

# appvuttr
Aplicação que consiste em uma API e Banco de Dados para a aplicação VUTTR (Very Useful Tools to Remember). A aplicação tem o próposito se servir para gerenciar ferramentas com seus respectivos detalhes. 

### Documentação da API disponível no Heroku:
* [Documentação Vuttr](https://appvuttrsi.herokuapp.com/swagger-ui.html)

### Representação da Documentação da API:

![image](https://user-images.githubusercontent.com/40266018/158865608-d3006bad-f314-4961-9ae7-911f2ab615cb.png)

### Representação do Modelo Relacional do Banco de Dados:

![banco de dados vuttr](https://user-images.githubusercontent.com/40266018/158865711-ce6c7eae-247d-4c60-a286-756a5f6ef34f.png)


### Utilização:
Para testar as funcionalidades da API RESTful que estão representadas através da documentação se faz necessário o uso de uma ferramenta como o [Postman](https://www.postman.com/) ou semelhante.

Após a ferramenta Postman pronta para uso, é necessário fazer os seguintes passos:

* **1 - Geração do Token de autenticação**

Requisição POST para a rota definida em (https://appvuttrsi.herokuapp.com/oauth/token) com todos parâmetros preenchidos no corpo da requisição como demonstrado a seguir.

![image](https://user-images.githubusercontent.com/40266018/158853147-0fad7a1a-2331-462d-af6b-9c78352ab2fd.png)

Informar na opção Authorization o tipo (Type) junto com o Username e Password.

![image](https://user-images.githubusercontent.com/40266018/158855578-d2f7b2c5-7b53-453f-8d27-42ddff3ac471.png)

* **2 - Informar o Token gerado nas requisições**

Após a geração do Token, informar ele no corpo das requisições HTTP definidas pelas rotas descritas através da Documentação da API. Exemplo: chave(Authorization), valor(Bearer + Token).

A seguir, a requisição HTTP do tipo GET que lista todas a ferramentas salvas (https://appvuttrsi.herokuapp.com/api/tools/).

![image](https://user-images.githubusercontent.com/40266018/158856956-3524134d-4b24-4af4-8088-e80a946962f5.png)

* **3 -  Testar as demais requisições da API**

Para isso, seguir o modelo do passo 2 informando sempre o Token gerado no passo 1 com base nos endpoints descritos na [Documentação Vuttr](https://appvuttrsi.herokuapp.com/swagger-ui.html), necessário preencher sempre os parâmetros exigidos em cada requisição. 

Como fazer: https://appvuttrsi.herokuapp.com + o prefixo da rota definida.

Exemplo: https://appvuttrsi.herokuapp.com/api/tools/ para listar todas as ferramentas.


### Ferramentas utilizadas no Desenvolvimento:

* **Spring Tool Suite** - Disponibiliza um ambiente de desenvolvimento, baseado no IDE Eclipse, com um kit de ferramentas pré-instaladas para o desenvolvimento de aplicações em Java utilizando tecnologias do Spring.
* **Postman** - É um API Client que facilita aos desenvolvedores criar, compartilhar, testar e documentar APIs. Isso é feito, permitindo aos usuários criar e salvar solicitações HTTP e HTTPs simples e complexas, bem como ler suas respostas.
* **MySQL Workbench** - É uma ferramenta visual de design de banco de dados que integra desenvolvimento, administração, design, criação e manutenção de SQL em um único ambiente de desenvolvimento integrado para o sistema de banco de dados MySQL.

### Demais Tecnologias utilizadas:

* **Java** - Linguagem para desenvolvimento da Aplicação.
* **Spring Framework** (Spring Boot, Spring Security, Spring Data ...) - Framework para desenvolvimento da aplicação junto com as suas dependências de configurações como a segurança, conexão com a base de dados, padrão web, entre outras. 
* **Swagger** - Linguagem para descrição e desenvolvimento da documentação da API. 
* **Flyway** -  Ferramenta para gerenciamento das migrações do banco de dados. 
* **MySQL** - Sistema para o gerenciamento do banco de dados, com a linguagem SQL como interface.
* **Git e GitHub** - Ferramentas para versionamento da aplicação e disponibilização do código.
* **Heroku** - Plataforma na nuvem para disponibilização funcional da aplicação. 
