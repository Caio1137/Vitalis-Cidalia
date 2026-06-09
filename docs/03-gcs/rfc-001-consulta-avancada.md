# RFC-001 - Consulta Avancada de Relatorios

## Descricao

Adicionar uma consulta avancada para que o administrador processe comandos textuais e filtre ocorrencias por tipo, prioridade, status, setor, bairro, ponto e atendimento.

## Justificativa

O Projeto Integrador exige simulacao de analisador lexico e sintatico. A funcionalidade tambem ajuda o gestor a gerar recortes rapidos sem criar uma tela de filtros para cada combinacao possivel.

## Impacto

| Area | Impacto |
|---|---|
| Backend | Novos componentes `ConsultaLexer`, `ConsultaParser`, `ConsultaExpression` e endpoint `/api/relatorios/consulta-avancada`. |
| Frontend | Nova tela `Consulta avancada`. |
| Banco | Sem alteracao estrutural. |
| Documentacao | Gramática, tabela preditiva e exemplos documentados em LFA. |

## Versao aplicada

`v1.0.0`

## Criterios de aceite

- Comando com `AND` deve ser aceito.
- Valores devem estar entre aspas.
- Campos nao permitidos devem retornar erro claro.
- Resultado deve exibir tokens, arvore simplificada e ocorrencias encontradas.
