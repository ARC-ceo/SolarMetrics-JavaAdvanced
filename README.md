
# SolarMetrics - API em Spring Boot

**SolarMetrics** é uma API desenvolvida para monitoramento e análise de energia solar, fornecendo dados em tempo real sobre sensores, usuários e ocupação de sistemas. A aplicação permite integrar sensores IoT, gerar relatórios e fornecer dados para aplicativos móveis ou dashboards web.

## Sobre o time

- **Carlos Clementino**: Responsável por construir a API em Java Spring Boot.  
- **Arthur Algate**: Responsável pelo banco de dados e Compliance QA.  
- **Eder Silva**: Responsável pela criação do APP mobile.

## Como rodar a aplicação

### Pré-requisitos
- Java 17 ou superior  
- Maven 3.8+  
- IDE recomendada: IntelliJ IDEA ou VSCode  

### Perfis de execução
A aplicação possui dois **profiles** de configuração de banco de dados:

1. **local**: utiliza o banco em memória H2, ideal para testes e desenvolvimento local.  
2. **prod**: conecta com o Oracle Database, utilizado para produção.

### Passos para executar

1. Clone o repositório:  
```bash
git clone https://github.com/SEU_USUARIO/SolarMetrics-API.git
```
2. Acesse a pasta do projeto:  
```bash
cd SolarMetrics-API
```
3. Configure o banco de dados no `application-prod.properties` (Oracle):
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

O profile **local** já está configurado para H2 e não precisa de configuração adicional.

4. Execute a aplicação com o profile desejado:

**Para local (H2):**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

**Para produção (Oracle):**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

5. A API estará disponível em: `http://localhost:8080`

### Testando a API
A documentação dos endpoints está disponível via **Swagger UI**:  
`http://localhost:8080/swagger-ui.html`

## Diagramas

### Diagrama de Classes
![Diagrama de classes](./docs/diagrama_classes.png)

### Arquitetura da Aplicação
![Arquitetura](./docs/arquitetura_app.png)

## Apresentação
Assista ao vídeo explicando a proposta tecnológica, o público-alvo e os problemas que a aplicação resolve:  
[Apresentação SolarMetrics](https://www.youtube.com/seu_link_aqui)

## Endpoints da API

A API foi documentada com **Swagger / OpenAPI**, oferecendo exemplos completos de requisição e resposta.  

### Endpoints principais

| Método | Endpoint                  | Descrição                                   |
|--------|---------------------------|--------------------------------------------|
| GET    | /api/sensores             | Lista todos os sensores cadastrados        |
| GET    | /api/sensores/{id}        | Retorna informações de um sensor específico |
| POST   | /api/sensores             | Cadastra um novo sensor                     |
| PUT    | /api/sensores/{id}        | Atualiza dados de um sensor                 |
| DELETE | /api/sensores/{id}        | Remove um sensor                             |
| GET    | /api/usuarios             | Lista todos os usuários do sistema          |
| POST   | /api/usuarios             | Cadastra um novo usuário                     |
| PUT    | /api/usuarios/{id}        | Atualiza informações de um usuário          |
| DELETE | /api/usuarios/{id}        | Remove um usuário                            |
| GET    | /api/ocupacao/{vagaoId}  | Consulta a ocupação de um vagão específico |
| POST   | /api/relatorios           | Gera relatório de energia / ocupação        |

> Para todos os endpoints, exemplos detalhados de request e response estão disponíveis no **Swagger UI**.

## Tecnologias utilizadas
- Java 17  
- Spring Boot 3.x  
- Spring Data JPA  
- Hibernate  
- H2 Database (local)  
- Oracle Database (prod)  
- Swagger / OpenAPI  

---

**SolarMetrics** — Monitoramento inteligente de energia solar em tempo real.
