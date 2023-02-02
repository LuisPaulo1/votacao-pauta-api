# Votacao-pauta-api

# Sobre o projeto

É uma API desenvolvida com Java e Spring Boot para gerenciamento de sessões de votação de pautas.
 
### Collection do Postman
Importar o link no Postman: https://api.postman.com/collections/6556841-828d6717-e696-477c-86bf-53928e5e1e8e?access_key=PMAT-01GNDRTFW5ZT2V2ETCPRXWJ6YG

### Documentação da API
http://localhost:8080/swagger-ui/index.html

### Passos e instruções para uso da Api votacao-pauta-api
- Cadastrar uma pauta no endpoint POST {host}/v1/pautas/cadastrar.
- Abrir uma sessão de votação no endpoint POST {host}/v1/sessoes/abrir-sessao-votacao.
- Na aberturação da sessão é obrigatório passar por parâmetro o id da pauta cadastrada anteriormente e opcionalmente o parâmetro tempoParaFechamento.
- Se não informar o valor do parâmetro tempoParaFechamento o valor padrão que é 60 segundos é enviado na requisição.
- Com a sessão de votação aberta pode ser realizado a votação no endpoint POST {host}/v1/votos/votar enquanto o tempo de fechamento da sessão não é encerrada.
- O parâmetro pautaId é obrigatório informar durante o voto e no corpo da requisição é preciso informar um CPF válido para o atributo cpfVotante e a resposta do voto que pode ser "Sim" ou "Não" para o atributo respostaDoVoto.
- A cada voto é preciso trocar o CPF, pois um votante só pode votar uma única vez.
- Para consultar o resultado e contagem de votos da pauta é necessário realizar uma requisição no endpoint GET {host}/v1/pautas/consultar/{id} informando o id da pauta.
- Se a consulta do resultado dos votos for feita após o encerramento da sessão é retornado o resultado final da sessão informando se a pauta foi aprovada ou não.
- A Api votacao-pauta-api disponibiliza o endpoint GET {host}/v1/users/{cpf} que verifica a partir do CPF do associado se ele pode votar.
- Esta validação é feita por um serviço externo chamado user-info-api no endpoint GET {host}/users/{cpf}

### Código fonte do serviço user-info-api
https://github.com/LuisPaulo1/user-info-api.git

## Arquitetura da aplicação
![Arquitetura](https://github.com/LuisPaulo1/assets/blob/master/votacao-pauta-api/arquitetura.png)

## Tecnologias utilizadas
- Java 11
- Spring (boot, web, data, validation)
- Spring Cloud Circuit Breaker (Resilience4J)
- JPA / Hibernate
- Lombok
- Maven
- Postgres
- JUnit/Mockito
- Swagger OpenAPI 3.0
- ModelMapper
- Docker

## Como executar a API votacao-pauta-api

## Opção 1 - Executando pelo Maven

### Pré-requisitos
- Java 11
- Banco de dados Postgres configurado no ambiente local com o Database votacao_pauta criado

```bash
# clonar repositório
git clone https://github.com/LuisPaulo1/votacao-pauta-api.git

# entrar na pasta do projeto votacao-pauta-api
cd votacao-pauta-api

# executar o projeto
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Observação para execução pelo maven
- Para realizar requisições no ambiente local no endpoint {host}/v1/users/{cpf} do serviço votacao-pauta-api é necessário que o serviço user-info-api esteja em execução no ambiente local.
- Dessa forma siga as instruções de execução no repositório https://github.com/LuisPaulo1/user-info-api.git

## Opção 2 - Executando pelo docker-compose

### Pré-requisitos
- Docker em execução no ambiente local

```bash
# clonar repositório
git clone https://github.com/LuisPaulo1/votacao-pauta-api.git

# entrar na pasta do projeto votacao-pauta-api
cd votacao-pauta-api

# executar o projeto
docker-compose up
```

# Autor

Luis Paulo

https://www.linkedin.com/in/luis-paulo-souza-a54358134/