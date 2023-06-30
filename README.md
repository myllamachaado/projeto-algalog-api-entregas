# Projeto de sistema de entregas utilizando Java e Spring do curso Mergulho Rest Algaworks.

<p align="center">
    <a alt="Java">
          <img src="https://img.shields.io/badge/Java-v17-blue.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring_Boot-v2.7.6-red.svg" />
    </a>
    <a alt="Maven">
        <img src="https://img.shields.io/badge/Maven-v4.0.0-brightgreen.svg" />
    </a>
    <a alt="JPA">
        <img src="https://img.shields.io/badge/JPA-v3.1.0-brightgreen.svg" />
    </a>
     <a alt="Validation">
        <img src="https://img.shields.io/badge/Validation-v2.0.2-red.svg" />
    </a>
     <a alt="Flyway">
        <img src="https://img.shields.io/badge/Flyway-v7.15.0-blueviolet.svg" />
    </a>
     <a alt="Model Mapper">
        <img src="https://img.shields.io/badge/Model_Mapper-v3.1.1-orange.svg" />
  </a>
  <a alt="Lombok">
          <img src="https://img.shields.io/badge/Lombok-v1.18.20.0-blue.svg" />
    </a>
    <a alt="MySQL">
        <img src="https://img.shields.io/badge/MySQL-v2.1.214-brightgreen.svg" />
    </a>
</p>

É um sistema básico, criado para o gerenciamento de entregas, por meio do cadastro dos principais dados de uma entrega, o registro dos relacionamentos com o respectivo Cliente e Destinatário, e o controle do fluxo da entrega, por meio do status, com a possibilidade de registrar o histórico de possíveis ocorrencias relacionadas às entregas.

## - Executando o projeto localmente

1)  Faça um clone do repositório:
`git clone https://github.com/myllamachaado/projeto-algalog-api-entregas.git`
2) Compile o projeto:
`mvn clean install`
3) Ajuste o application.properties para comportaras configurações do seu banco de dados nos seguintes localis:
```
spring.datasource.url=jdbc:mysql://<endereço do seu servidor local>:3306/algalog
spring.datasource.username=<seu usuário>
spring.datasource.password=<sua senha>

spring.flyway.url=jdbc:mysql://<endereço do seu servidor local>:3306/algalog
spring.flyway.user=<seu usuario>
spring.flyway.password=<sua senha>
```
4)  Execute o projeto:
`mvn spring-boot::run` 




## - Executando o projeto via Docker

1)  Faça um clone do repositório:
`git clone https://github.com/myllamachaado/projeto-algalog-api-entregas.git`
2) Compile o projeto:
`mvn clean install`
3)  Execute o projeto:
`docker-compose up -d`


