# Vitalis Cidalia

Sistema web para apoiar a gestao de atendimentos de emergencia na cidade ficticia de Cidalia.

Projeto Integrador - 5o Periodo de Engenharia de Software  
Alunos: Caio Caetano, Ruben Borges, Pedro Lucas, Alexandre Vinicius e Natanael Silva

## Stack

- Backend: Java 17, Spring Boot 3.x, Spring Data JPA, Bean Validation e PostgreSQL.
- Frontend: Angular, TypeScript, Reactive Forms, Services e Routing.
- GCS: Git, GitHub, Issues, Pull Requests, SemVer, CHANGELOG e GitHub Actions.

## Estrutura

```text
backend/                 API REST Spring Boot
frontend/                Aplicacao Angular
docs/                    Documentacao do Projeto Integrador
.github/workflows/       Pipeline de CI
```

## Como executar no Windows

Pré-requisitos:

- Java 17 instalado.
- Node.js 20 LTS e npm instalados.
- PostgreSQL instalado e rodando.

Banco esperado:

```powershell
createdb -U postgres vitalis_cidalia
```

Se seu Postgres usar outro usuario ou senha:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/vitalis_cidalia"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
```

Backend:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

Frontend:

```powershell
cd frontend
npm.cmd install
npm.cmd start
```

Portas padrao:

- API: `http://localhost:8080/api`
- Angular: `http://localhost:4200`

## Perfil de producao

O perfil padrao foi mantido para demonstracao local com PostgreSQL. Para uma implantacao real, use o perfil `prod`, que exige variaveis de ambiente e valida o schema do banco em vez de atualiza-lo automaticamente:

```powershell
$env:SPRING_PROFILES_ACTIVE="prod"
$env:DB_URL="jdbc:postgresql://host:5432/vitalis_cidalia"
$env:DB_USERNAME="usuario"
$env:DB_PASSWORD="senha"
$env:CORS_ALLOWED_ORIGIN="https://dominio-do-frontend"
cd backend
.\mvnw.cmd spring-boot:run
```

## Funcionalidades

- Cadastro e acompanhamento de ocorrencias.
- Gerenciamento de pontos de atendimento, equipes e veiculos.
- Despacho de atendimentos com rota resumida, distancia e tempo estimado.
- Dashboard operacional.
- Consulta avancada para relatorios administrativos.
- Validacoes com REGEX em campos como placa, CPF/CNPJ, telefone, CEP, protocolo e codigo de atendimento.

## Documentos principais

- [Especificacao funcional](docs/01-requisitos/especificacao-funcional.md)
- [Validacoes REGEX e automatos](docs/02-lfa/validacoes-regex.md)
- [Gramatica da consulta avancada](docs/02-lfa/consulta-avancada-gramatica.md)
- [Plano de GCS](docs/03-gcs/plano-gcs.md)
- [Matriz de rastreabilidade](docs/03-gcs/matriz-rastreabilidade.md)
- [Arquitetura AWS](docs/04-aws/arquitetura-aws.md)
- [Padroes de Projeto](docs/05-padroes/padroes-projeto.md)

## Repositorio

Remoto definido para entrega: `https://github.com/Caio1137/Vitalis-Cidalia.git`

