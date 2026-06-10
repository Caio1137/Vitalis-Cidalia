# Padroes de Projeto

## Padroes aplicados diretamente

| Padrao | Onde aparece | Comentario |
|---|---|---|
| Singleton | Services, Controllers e Mappers anotados com `@Service`, `@RestController` e `@Component`. | O Spring cria uma instancia compartilhada por padrao para esses beans. |
| Factory Method | `CodigoOperacionalFactory`. | Cria protocolo de ocorrencia e codigo de atendimento em um ponto unico. |
| Adapter | Pacote `mapper`. | Adapta entidades JPA para DTOs usados pela API. |
| Template Method | `AbstractCrudService`. | Define o fluxo `salvar -> validar -> persistir`; services especializam validacoes. |
| Iterator | Uso de `stream()` no backend e `@for` no Angular. | Percorre colecoes de ocorrencias, atendimentos e recursos. |
| Decorator | `ApiExceptionHandler` sobre respostas de erro. | Acrescenta estrutura padronizada sem mudar a excecao original. |

## Observacao para apresentacao

Os comentarios principais estao no codigo em:

- `AbstractCrudService.java`
- `CodigoOperacionalFactory.java`
- `RegexPatterns.java`

Na apresentacao, o grupo pode mostrar estes arquivos e explicar por que cada padrao foi usado sem forcar complexidade desnecessaria.
