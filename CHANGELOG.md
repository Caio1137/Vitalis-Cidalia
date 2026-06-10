# Changelog

## [1.0.2] - 2026-06-10

### Changed

- Atualizada a versao final da entrega para `v1.0.2`.
- Registrada a baseline final de auditoria em GCS.
- Reforcada a rastreabilidade de Issues, branches, Pull Requests, arquivos e releases.
- Documentada a branch protection ativa em `main` e `develop`.
- Ajustada a documentacao de Padroes de Projeto para manter apenas os padroes aplicados diretamente no sistema.

### Fixed

- Removida duplicidade de `spring.application.name` na configuracao do backend.
- Incluido comentario de aplicacao do padrao Adapter no mapper de ocorrencias.

## [1.0.1] - 2026-06-09

### Fixed

- Ajustado o pipeline CI para executar o Maven Wrapper corretamente no GitHub Actions.

## [1.0.0] - 2026-06-09

### Added

- Backend Spring Boot com camadas Controller, Service e Repository.
- Frontend Angular com rotas, services e componentes por area.
- Cadastro e filtros de ocorrencias.
- Gerenciamento de pontos, equipes e veiculos.
- Despacho e acompanhamento de atendimentos.
- Dashboard operacional.
- Consulta avancada com analisador lexico e sintatico.
- Validacoes por REGEX e testes unitarios.
- Documentacao de LFA, GCS, AWS, requisitos e padroes.
- Pipeline CI para backend e frontend.

## [0.1.0] - 2026-06-09

### Added

- Estrutura inicial do projeto.
- Configuracao base de backend, frontend e documentacao.
