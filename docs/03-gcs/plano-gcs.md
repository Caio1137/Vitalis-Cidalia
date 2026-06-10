# Plano de Gerencia de Configuracao de Software

## Repositorio

Repositorio publico previsto: `https://github.com/Caio1137/Vitalis-Cidalia.git`

## Itens de Configuracao

| IC | Tipo | Localizacao |
|---|---|---|
| Backend Spring Boot | Codigo-fonte | `backend/` |
| Frontend Angular | Codigo-fonte | `frontend/` |
| Banco PostgreSQL | Configuracao | `backend/src/main/resources/application.properties` |
| Pipeline CI | Automacao | `.github/workflows/ci.yml` |
| Documentacao | Artefato tecnico | `docs/` |
| Maven Wrapper | Build backend | `backend/mvnw.cmd`, `backend/.mvn/` |
| npm lockfile | Build frontend | `frontend/package-lock.json` |
| Changelog | Registro de status | `CHANGELOG.md` |

## Baselines

| Baseline | Tag | Composicao | Criterio de estabilidade |
|---|---|---|---|
| BL0 | `v0.1.0` | Estrutura inicial, README, backend/frontend criados, docs base. | Projeto compila localmente e CI configurado. |
| BL1 | `v1.0.0` | Sistema funcional com ocorrencias, recursos, atendimentos, dashboard, consulta avancada e documentacao final. | Backend e frontend com build verde, docs revisados e matriz atualizada. |
| BL2 | `v1.0.2` | Entrega final revisada apos conferencia do PDF, com GCS, versao, rastreabilidade e branch protection atualizadas. | CI verde, changelog atualizado, release final publicada e pasta limpa para entrega. |

## Versionamento

O projeto usa Semantic Versioning:

- `MAJOR`: quebra de compatibilidade ou mudanca grande de escopo.
- `MINOR`: nova funcionalidade compativel.
- `PATCH`: correcao de bug ou ajuste pequeno.

Versao de entrega: `1.0.2`.

## Controle de mudancas

Fluxo planejado para alteracoes:

1. Criar Issue no GitHub para cada requisito.
2. Criar branch a partir de `develop`.
3. Implementar com commits convencionais.
4. Abrir Pull Request para `develop`.
5. Validar CI.
6. Fazer merge.
7. Registrar release em `CHANGELOG.md`.

Evidencias registradas:

- Issues de Change Request para requisitos funcionais e entregas tecnicas.
- Branches de feature/documentacao separadas por requisito.
- Pull Requests vinculados aos requisitos e validados pelo GitHub Actions.
- Tags e releases para marcar baselines entregaveis.
- Matriz de rastreabilidade com Issue, branch, PR, arquivos e release.

Branches utilizadas:

- `feature/rf01-pontos-atendimento`
- `feature/rf02-veiculos`
- `feature/rf03-equipes`
- `feature/rf04-ocorrencias`
- `feature/rf05-filtros-ocorrencias`
- `feature/rf06-despacho-atendimento`
- `feature/rf07-status-atendimento`
- `feature/rf08-dashboard`
- `feature/rf09-consulta-avancada`
- `docs/lfa-regex-automatos-gramatica`
- `docs/gcs-baselines-ci-rastreabilidade`
- `docs/aws-arquitetura-custos`
- `docs/ajustar-rastreabilidade-issues-prs`
- `docs/registrar-branch-protection`
- `docs/fechamento-v1.0.2`

## Observacao sobre rastreabilidade

Na revisao final, a equipe consolidou a ligacao entre requisitos, Issues, branches, Pull Requests, arquivos principais e releases. O objetivo desta rastreabilidade e permitir auditoria do trabalho entregue sem alterar o historico original do GitHub.

## Protecao de branches

A branch protection esta ativa no GitHub para as branches principais do fluxo:

| Branch | PR obrigatorio | Revisoes | Checks obrigatorios | Conversas resolvidas | Force push e exclusao |
|---|---|---|---|---|---|
| `main` | Sim | 1 aprovacao | Backend e frontend | Sim | Bloqueados |
| `develop` | Sim | 1 aprovacao | Backend e frontend | Sim | Bloqueados |

Checks obrigatorios:

- `Backend - Java 17 / Maven`
- `Frontend - Angular / Node 20`

## Auditoria

Auditoria automatica:

- Backend: `mvnw.cmd test`.
- Frontend: `npm ci` e `npm run build`.

Auditoria manual:

- Conferir se os requisitos estao ligados a arquivos e documentos.
- Conferir se REGEX, automatos e gramatica estao documentados.
- Conferir se README executa no Windows.
- Conferir se nenhuma senha real foi versionada.
