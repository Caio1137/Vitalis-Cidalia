# Matriz de Rastreabilidade

| Req. | Issue | Branch | PR | Arquivos principais | Release |
|---|---|---|---|---|---|
| RF01 | #1 | `feature/rf01-pontos-atendimento` | #13 | `PontoAtendimento*`, `PontoAtendimentoController`, `recursos.component.*` | v1.0.0 |
| RF02 | #2 | `feature/rf02-veiculos` | #14 | `Veiculo*`, `VeiculoController`, `recursos.component.*` | v1.0.0 |
| RF03 | #3 | `feature/rf03-equipes` | #15 | `Equipe*`, `EquipeController`, `recursos.component.*` | v1.0.0 |
| RF04 | #4 | `feature/rf04-ocorrencias` | #16 | `Ocorrencia*`, `OcorrenciaController`, `ocorrencias.component.*` | v1.0.0 |
| RF05 | #5 | `feature/rf05-filtros-ocorrencias` | #17 | `OcorrenciaRepository`, `OcorrenciaService`, `ocorrencias.component.*` | v1.0.0 |
| RF06 | #6 | `feature/rf06-despacho-atendimento` | #18 | `Atendimento*`, `AtendimentoService`, `atendimentos.component.*` | v1.0.0 |
| RF07 | #7 | `feature/rf07-status-atendimento` | #19 | `AtendimentoService`, `AtendimentoController` | v1.0.0 |
| RF08 | #8 | `feature/rf08-dashboard` | #20 | `DashboardService`, `DashboardController`, `dashboard.component.*` | v1.0.0 |
| RF09 | #9 | `feature/rf09-consulta-avancada` | #21 | `ConsultaLexer`, `ConsultaParser`, `ConsultaAvancadaService`, `consulta-avancada.component.*` | v1.0.0 |
| LFA | #10 | `docs/lfa-regex-automatos-gramatica` | #22 | `RegexPatterns`, `docs/02-lfa/*` | v1.0.0 |
| GCS | #11 | `docs/gcs-baselines-ci-rastreabilidade` | #23 | `docs/03-gcs/*`, `.github/workflows/ci.yml`, `CHANGELOG.md` | v1.0.1 |
| AWS | #12 | `docs/aws-arquitetura-custos` | #24 | `docs/04-aws/arquitetura-aws.md` | v1.0.0 |
| GCS-AJUSTE | #25 | `docs/ajustar-rastreabilidade-issues-prs` | #26/#27 | `docs/03-gcs/matriz-rastreabilidade.md`, `docs/03-gcs/plano-gcs.md` | v1.0.2 |
| GCS-PROTECAO | #28 | `docs/registrar-branch-protection` | #29/#30 | `docs/03-gcs/plano-gcs.md` | v1.0.2 |
| GCS-FECHAMENTO | #31 | `docs/fechamento-v1.0.2` | #32 | `CHANGELOG.md`, `backend/pom.xml`, `frontend/package.json`, `docs/03-gcs/*` | v1.0.2 |

## Observacao de auditoria

Esta matriz consolida a rastreabilidade auditavel da entrega. Cada requisito possui uma Issue de Change Request, uma branch correspondente, um PR fechado, arquivos principais e release associada. O registro preserva o historico original do GitHub e documenta como cada parte do projeto foi revisada no fechamento de GCS.
