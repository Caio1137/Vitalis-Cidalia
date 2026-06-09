# Especificacao Funcional - Vitalis Cidalia

## Visao geral

A Vitalis Cidalia e uma solucao web para apoiar a Secretaria Municipal de Saude da cidade ficticia de Cidalia no registro, acompanhamento e analise de atendimentos de emergencia.

O sistema foi pensado para uma central operacional pequena, com dados suficientes para demonstrar arquitetura em camadas, relacionamento JPA, validacoes, dashboard e consulta avancada.

## Escopo entregue

Incluido:

- Gerenciamento de ocorrencias.
- Gerenciamento de pontos de atendimento.
- Gerenciamento de equipes e veiculos.
- Despacho e acompanhamento de atendimentos.
- Dashboard com indicadores.
- Consulta avancada por comando textual.
- Validacoes por REGEX.
- Documentacao de GCS, AWS, LFA e Padroes de Projeto.

Fora do escopo:

- Autenticacao de usuarios.
- Integracao real com mapas.
- Envio real de SMS, e-mail ou radio.
- Deploy em nuvem.

## Requisitos funcionais

| Codigo | Requisito | Criterios de aceite |
|---|---|---|
| RF01 | Gerenciar pontos de atendimento | Cadastrar, listar e inativar pontos sem ocorrencias vinculadas. |
| RF02 | Gerenciar veiculos | Cadastrar veiculos com placa validada e acompanhar status operacional. |
| RF03 | Gerenciar equipes | Cadastrar equipes, vincular veiculo e acompanhar disponibilidade. |
| RF04 | Registrar ocorrencias | Informar solicitante, documento, telefone, tipo, prioridade, endereco, bairro, descricao e ponto de destino. |
| RF05 | Acompanhar ocorrencias | Listar e filtrar por status, prioridade, tipo e setor. |
| RF06 | Despachar atendimento | Vincular ocorrencia, equipe e veiculo disponiveis, registrando rota, distancia e tempo estimado. |
| RF07 | Atualizar atendimento | Alterar status do atendimento e liberar equipe/veiculo ao finalizar ou cancelar. |
| RF08 | Consultar dashboard | Exibir ocorrencias abertas, criticas, recursos disponiveis e ultimos registros. |
| RF09 | Consulta avancada | Processar comandos como `parametro.tipo = "CARDIACA" AND parametro.setor = "NORTE"`. |

## Regras de negocio

| Codigo | Regra |
|---|---|
| RN01 | Placa deve seguir o formato Mercosul `AAA1A11`. |
| RN02 | CPF/CNPJ, telefone, CEP, protocolo e codigo de atendimento devem respeitar as REGEX documentadas. |
| RN03 | Uma ocorrencia so pode ter um atendimento vinculado. |
| RN04 | Atendimento so pode ser despachado com equipe e veiculo disponiveis. |
| RN05 | Ao despachar atendimento, equipe e veiculo passam para `EM_ATENDIMENTO`. |
| RN06 | Ao finalizar ou cancelar atendimento, equipe e veiculo voltam para `DISPONIVEL`. |
| RN07 | Ponto de atendimento com ocorrencias vinculadas nao pode ser inativado. |

## Modelo principal

- `PontoAtendimento` 1:N `Ocorrencia`
- `Equipe` 1:1 `Veiculo`
- `Ocorrencia` 1:1 `Atendimento`
- `Atendimento` N:1 `Equipe`
- `Atendimento` N:1 `Veiculo`

## Endpoints

| Metodo | Rota | Uso |
|---|---|---|
| GET | `/api/dashboard` | Indicadores operacionais. |
| GET/POST | `/api/pontos-atendimento` | Listar e cadastrar pontos. |
| GET/POST | `/api/veiculos` | Listar e cadastrar veiculos. |
| PATCH | `/api/veiculos/{id}/status` | Alterar status do veiculo. |
| GET/POST | `/api/equipes` | Listar e cadastrar equipes. |
| PATCH | `/api/equipes/{id}/status` | Alterar status da equipe. |
| GET/POST | `/api/ocorrencias` | Listar/filtrar e registrar ocorrencias. |
| PATCH | `/api/ocorrencias/{id}/status` | Alterar status da ocorrencia. |
| GET/POST | `/api/atendimentos` | Listar e despachar atendimentos. |
| PATCH | `/api/atendimentos/{id}` | Atualizar atendimento. |
| POST | `/api/relatorios/consulta-avancada` | Executar consulta textual. |
