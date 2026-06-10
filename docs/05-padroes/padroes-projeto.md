# Padroes de Projeto

## Padroes aplicados diretamente

| Padrao | Onde aparece | Comentario |
|---|---|---|
| Singleton | Services, Controllers e Mappers anotados com `@Service`, `@RestController` e `@Component`. | O Spring cria uma instancia compartilhada por padrao para esses beans. |
| Factory Method | `CodigoOperacionalFactory`. | Cria protocolo de ocorrencia e codigo de atendimento em um ponto unico. |
| Adapter | Pacote `mapper`. | Adapta entidades JPA para DTOs usados pela API. |
| Template Method | `AbstractCrudService`. | Define o fluxo `salvar -> validar -> persistir`; services especializam validacoes. |
| Composite | `ConsultaExpression`, `ConditionExpression` e `AndExpression`. | Monta a arvore da consulta avancada combinando condicoes simples e operadores logicos. |
| Iterator | Uso de `stream()` no backend e `@for` no Angular. | Percorre colecoes de ocorrencias, atendimentos e recursos. |
| Decorator | `ApiExceptionHandler` sobre respostas de erro. | Acrescenta estrutura padronizada sem mudar a excecao original. |

## Observacao para apresentacao

Os comentarios principais estao no codigo em:

- `AbstractCrudService.java`
- `CodigoOperacionalFactory.java`
- `OcorrenciaMapper.java`
- `ConsultaExpression.java`
- `AndExpression.java`
- `ApiExceptionHandler.java`
- `DashboardService.java`
- `RegexPatterns.java`
