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
