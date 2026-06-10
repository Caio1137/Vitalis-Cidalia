# Backend - Vitalis Cidalia

API REST em Spring Boot 3.x com Java 17, JPA e PostgreSQL.

Comandos no Windows:

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Variaveis aceitas:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/vitalis_cidalia"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
```

Perfil de producao:

```powershell
$env:SPRING_PROFILES_ACTIVE="prod"
$env:DB_URL="jdbc:postgresql://host:5432/vitalis_cidalia"
$env:DB_USERNAME="usuario"
$env:DB_PASSWORD="senha"
$env:CORS_ALLOWED_ORIGIN="https://dominio-do-frontend"
.\mvnw.cmd spring-boot:run
```

No perfil `prod`, o Hibernate usa `ddl-auto=validate`. O schema precisa existir previamente no PostgreSQL.
