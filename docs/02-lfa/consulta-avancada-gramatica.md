# LFA - Consulta Avancada

## Objetivo

A consulta avancada permite que o administrador filtre ocorrencias por comando textual.

Exemplo aceito:

```text
parametro.tipo = "CARDIACA" AND parametro.setor = "NORTE"
```

## Tokens do analisador lexico

| Token | Exemplo |
|---|---|
| IDENTIFICADOR | `parametro`, `tipo`, `setor` |
| PONTO | `.` |
| IGUAL | `=` |
| DIFERENTE | `!=` |
| STRING | `"CARDIACA"` |
| AND | `AND` |
| EOF | fim da entrada |

Implementacao: `backend/src/main/java/br/com/vitalis/cidalia/service/consulta/ConsultaLexer.java`

## Gramatica livre de contexto

```text
<consulta>  -> <condicao> <consulta_linha>
<consulta_linha> -> AND <condicao> <consulta_linha> | ε
<condicao> -> IDENTIFICADOR PONTO IDENTIFICADOR <operador> STRING
<operador> -> IGUAL | DIFERENTE
```

## Tecnica sintatica escolhida

Foi usada analise descendente recursiva, equivalente a um analisador preditivo LL(1) para esta gramatica simples.

Motivo:

- A gramatica nao possui recursao a esquerda.
- Cada producao pode ser escolhida com um simbolo de lookahead.
- A implementacao fica clara para demonstrar no codigo e na apresentacao.

Implementacao: `backend/src/main/java/br/com/vitalis/cidalia/service/consulta/ConsultaParser.java`

## Tabela preditiva

| Nao terminal | IDENTIFICADOR | AND | EOF |
|---|---|---|---|
| `<consulta>` | `<consulta> -> <condicao> <consulta_linha>` | erro | erro |
| `<consulta_linha>` | erro | `<consulta_linha> -> AND <condicao> <consulta_linha>` | `<consulta_linha> -> ε` |
| `<condicao>` | `<condicao> -> IDENTIFICADOR PONTO IDENTIFICADOR <operador> STRING` | erro | erro |
| `<operador>` | erro | erro | erro |

Para `<operador>`, a escolha e feita por `IGUAL` ou `DIFERENTE`.

| Nao terminal | IGUAL | DIFERENTE |
|---|---|---|
| `<operador>` | `<operador> -> IGUAL` | `<operador> -> DIFERENTE` |

## Campos permitidos

| Campo | Origem |
|---|---|
| `parametro.tipo` ou `ocorrencia.tipo` | Tipo da ocorrencia |
| `parametro.status` ou `ocorrencia.status` | Status da ocorrencia |
| `parametro.prioridade` ou `ocorrencia.prioridade` | Prioridade da ocorrencia |
| `parametro.setor`, `ocorrencia.setor` ou `ponto.setor` | Setor do ponto de destino |
| `ocorrencia.bairro` | Bairro informado na ocorrencia |
| `ponto.tipo` | Tipo do ponto de atendimento |
| `atendimento.status` | Status do atendimento vinculado |

## Exemplo de saida

Entrada:

```text
parametro.tipo = "CARDIACA" AND parametro.setor = "NORTE"
```

Tokens:

```text
IDENTIFICADOR(parametro), PONTO(.), IDENTIFICADOR(tipo), IGUAL(=), STRING(CARDIACA), AND(AND), ...
```

Arvore sintatica simplificada:

```text
AND((parametro.tipo = "CARDIACA"), (parametro.setor = "NORTE"))
```
