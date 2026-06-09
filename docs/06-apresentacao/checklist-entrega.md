# Checklist de Apresentacao

## Antes de apresentar

- [ ] Confirmar que o Postgres esta rodando.
- [ ] Confirmar banco `vitalis_cidalia`.
- [ ] Rodar backend com `.\mvnw.cmd spring-boot:run`.
- [ ] Rodar frontend com `npm.cmd start`.
- [ ] Abrir `http://localhost:4200`.
- [ ] Mostrar dashboard com dados iniciais.
- [ ] Registrar uma ocorrencia.
- [ ] Despachar um atendimento.
- [ ] Executar consulta avancada.
- [ ] Mostrar documentos de LFA, GCS, AWS e Padroes.

## Sequencia sugerida

1. Contexto da Vitalis Tech e da cidade de Cidalia.
2. Arquitetura: Angular, Spring Boot, PostgreSQL e camadas.
3. Fluxo principal: ocorrencia -> atendimento -> status.
4. Validacoes REGEX e automatos.
5. Consulta avancada com lexer/parser.
6. Padroes de Projeto aplicados.
7. GCS: ICs, baselines, SemVer, CI, matriz e RFC.
8. Arquitetura AWS e estimativa de custo.
