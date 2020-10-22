# marvel-backend

## Descrição
Reimplementação dos endpoints de **characters** da [API da Marvel](https://developer.marvel.com/docs#!/public)

## Objetivo
Apresentação da proposta técnica em processo seletivo

## Tecnologias Utilizadas

### Back-end
* Java 14
* Spring Boot
* JUnit 5
* H2 Database
* Maven

## Execução do projeto
* Clonar ou baixar o [projeto](https://github.com/vandsonlima/marvel-backend.git)
  ```./mvnw clean package && java -jar target/marvel-0.0.1-SNAPSHOT.jar```

## Como acessar e testar
* Acessar os endpoints a partir da url http://localhost:8080
- /v1/public/characters
- /v1/public/characters/{characterId}
- /v1/public/characters/{characterId}/comics
- /v1/public/characters/{characterId}/events
- /v1/public/characters/{characterId}/series
- /v1/public/characters/{characterId}/stories

## Como executar os testes unitários
Para executar os testes  basta realizar o comando ``` ./mvnw test ```

## Observações de implementação
* No endpoint ```/v1/public/characters/{characterId}/comics``` alguns filtros não foram implementados para poder seguir 
com demais features. Os filtros implementados foram: 
    - title
    - titleStartsWith
    - formatComic
    - formatType
* Ainda no endpoint de **comics** foram suprimidas a implementação ou validação de alguns campos como ```variants```, ```collections``` e ```collectionIssues```.
* A ordenação só está aceitando uma opção de ordenação.
* Os endpoints ```events``` e ```series``` estão sem o next e previous
* O endpoint ```/v1/public/characters/{characterId}/stories``` funciona apenas com a listagem completa, sem filtros e sem parâmetros de paginação e ordenação

