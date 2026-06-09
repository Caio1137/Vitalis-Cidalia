# LFA - Validacoes REGEX e Automatos

## Tabela de entradas validadas

| Dado validado | Expressao regular | Onde aparece |
|---|---|---|
| Placa Mercosul | `^[A-Z]{3}[0-9][A-Z][0-9]{2}$` | `RegexPatterns.PLACA_MERCOSUL`, DTO de veiculo e entidade `Veiculo`. |
| CPF ou CNPJ | `^(\d{3}\.?\d{3}\.?\d{3}-?\d{2}|\d{2}\.?\d{3}\.?\d{3}/?\d{4}-?\d{2})$` | DTO de ocorrencia e entidade `Ocorrencia`. |
| Telefone brasileiro | `^\(?\d{2}\)?\s?9?\d{4}-?\d{4}$` | Pontos, equipes e solicitante da ocorrencia. |
| CEP | `^\d{5}-?\d{3}$` | Pontos de atendimento. |
| Protocolo de ocorrencia | `^CID-\d{4}-\d{6}$` | Gerado pelo backend em `CodigoOperacionalFactory`. |
| Codigo de atendimento | `^ATD-\d{6}$` | Gerado pelo backend em `CodigoOperacionalFactory`. |

## Automato 1 - Placa Mercosul `AAA1A11`

Alfabeto simplificado: `L` representa letra maiuscula e `D` representa digito.

```mermaid
stateDiagram-v2
    [*] --> q0
    q0 --> q1: L
    q1 --> q2: L
    q2 --> q3: L
    q3 --> q4: D
    q4 --> q5: L
    q5 --> q6: D
    q6 --> q7: D
    q7 --> [*]
```

Estados de aceitacao: `q7`.

## Automato 2 - CEP `00000-000` ou `00000000`

```mermaid
stateDiagram-v2
    [*] --> q0
    q0 --> q1: D
    q1 --> q2: D
    q2 --> q3: D
    q3 --> q4: D
    q4 --> q5: D
    q5 --> q6: -
    q5 --> q7: D
    q6 --> q7: D
    q7 --> q8: D
    q8 --> q9: D
    q9 --> [*]
```

Estados de aceitacao: `q9`.

## Automato 3 - Codigo de atendimento `ATD-000000`

```mermaid
stateDiagram-v2
    [*] --> q0
    q0 --> q1: A
    q1 --> q2: T
    q2 --> q3: D
    q3 --> q4: -
    q4 --> q5: D
    q5 --> q6: D
    q6 --> q7: D
    q7 --> q8: D
    q8 --> q9: D
    q9 --> q10: D
    q10 --> [*]
```

Estados de aceitacao: `q10`.

## Trecho de codigo

```java
public static final String PLACA_MERCOSUL = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$";
public static final String CPF_CNPJ = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}|\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2})$";
```

As expressoes estao centralizadas em `backend/src/main/java/br/com/vitalis/cidalia/validation/RegexPatterns.java` e sao aplicadas com `@Pattern` nos DTOs e entidades.
