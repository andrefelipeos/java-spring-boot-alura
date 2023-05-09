# spring-boot-alura
Atividades desenvolvidas na formação Java e Spring Boot da Alura

## Curso I: desenvolvendo uma API Rest

Na primeira parte dessa formação foi desenvolvido uma API Rest utilizando Spring. 
Essa API corresponde ao *back-end* de uma aplicação de gerenciamento de consultas médicas. 
Essa aplicação busca atender as demandas da clínica fictícia Voll Med. 
Nela é possível cadastrar e listar médicos e pacientes. 
Também é possível remover e alterar os dados cadastrados. 

O que foi feito ou utilizado nessa fase do projeto:
- Foram criados CRUDs para médicos e pacientes
- As consultas foram paginadas e ordenadas com recursos do próprio Spring
- Foi implementado um esquema de *soft delete* para exclusão de dados
- Os dados recebidos pela API foram validados com Bean Validation
- A API foi testada com o Insomnia
- O padrão DTO foi implementado utilizando *records* do Java
- O banco de dados utilizado foi o MariaDB
- O Spring Data e a JPA tornaram a persistência mais simples
- O esquema do banco de dados foi evoluído com *migrations* do Flyway
- O Maven gerenciou as dependências, inclusive os módulos do Spring Boot
- A biblioteca Lombok foi usada para gerar código *boilerplate*

## Curso II: aplicando boas práticas e protegendo a API

No segundo curso da formação foi adicionada uma classe para tratamento de erros.
Com ela é retornado o código HTTP adequando para cada situação de exceção.
Como, por exemplo, os códigos 201 - Created e 404 - Not Found.

Também foram adicionados processos de autenticação e autorização para usuários.
Para isso foram utilizados o módulo Spring Security e a biblioteca Auth0 JWT.
Dessa forma, algumas requisições passam a ser atendidas somente se houver um *token* JWT válido em seu cabeçalho.
Um *token* JWT é gerado sempre que um usuário faz *login* com sua senha e seu nome de usuário.

## Curso III: documentação, testes automatizados e *build* da API

Os principais acréscimos feitos no curso III foram a documentação e os testes automatizados.

A documentação foi gerada utilizando a biblioteca SpringDoc/OpenAPI. Essa biblioteca gera dois *endpoints* que listam todos os outros *endpoints* disponibilizadas pela API, incluindo seus tipos de parâmetros e retornos esperados. Um dos *endpoints* da documentação retorna as informações no formato JSON. O outro retorna uma interface web gráfica, por onde é possivel testar a API, substituindo softwares como Insomnia e Postman.

Testes de unidade foram criados utilizando a biblioteca JUnit 5, porém eles só cobrem uma pequena parte do código. Por escolha, os testes utilizam seu próprio banco de dados MariaDB, e não um banco de dados em memória. A url do banco de dados de testes foi definida em um arquivo de propriedades próprio, que configura a profile *test*.
