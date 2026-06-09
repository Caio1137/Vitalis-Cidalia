# Padroes de Projeto

## Padroes aplicados diretamente

| Padrao | Onde aparece | Comentario |
|---|---|---|
| Singleton | Services, Controllers e Mappers anotados com `@Service`, `@RestController` e `@Component`. | O Spring cria uma instancia compartilhada por padrao para esses beans. |
| Factory Method | `CodigoOperacionalFactory`. | Cria protocolo de ocorrencia e codigo de atendimento em um ponto unico. |
| Adapter | Pacote `mapper`. | Adapta entidades JPA para DTOs usados pela API. |
| Template Method | `AbstractCrudService`. | Define o fluxo `salvar -> validar -> persistir`; services especializam validacoes. |
| Interpreter | Pacote `service.consulta`. | Cada expressao da consulta avancada avalia uma ocorrencia. |
| Iterator | Uso de `stream()` no backend e `@for` no Angular. | Percorre colecoes de ocorrencias, atendimentos e recursos. |
| Decorator | `ApiExceptionHandler` sobre respostas de erro. | Acrescenta estrutura padronizada sem mudar a excecao original. |

## GRASP

| Padrao GRASP | Aplicacao no projeto |
|---|---|
| Controller | Controllers REST recebem requisicoes e delegam para services. |
| Information Expert | Services concentram regras do dominio que possuem os dados necessarios. |
| Creator | Services criam entidades agregando dependencias, como atendimento com equipe, veiculo e ocorrencia. |
| Low Coupling | Frontend usa services HTTP; backend separa Controller, Service, Repository e Mapper. |
| High Cohesion | Cada classe tem responsabilidade curta: controller expõe API, service aplica regra, repository persiste. |
| Indirection | DTOs e mappers evitam acoplamento direto entre Angular e entidades JPA. |
| Protected Variations | Interfaces de repository e DTOs isolam mudancas internas de persistencia. |

## Observacao para apresentacao

Os comentarios principais estao no codigo em:

- `AbstractCrudService.java`
- `CodigoOperacionalFactory.java`
- `ConsultaExpression.java`
- `RegexPatterns.java`

Na apresentacao, o grupo pode mostrar estes arquivos e explicar por que cada padrao foi usado sem forcar complexidade desnecessaria.
