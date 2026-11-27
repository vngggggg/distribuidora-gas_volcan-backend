# Backend - Distribuidora Gas El Volcan

Api Rest con Spring Boot 4.1.1 y java 21
Base de datos PotgreSQL
Autenticacion JWT y Roles (ADMIN y Cliente)

Frontend: https://github.com/vngggggg/distribuidora-gas-volcan.git

# Variables de entorno
Variable - Uso - Valor x defecto
----------------------
DB_URL | 
URL de la base: jdbc:postgresql://localhost:5432/Gasvolcan
----------------------
DB_USER | DB_PASSWORD
Usuario de PostgreSQL: postgres
Password: (debe definirse)
----------------------
JWT_SECRET |
Clave de firma de JWT (32 caracteres o mas)
clave de desarrollo local

# Como Ejecutar
Windows (PowerShell), dentro de la carpeta del backend

| $env:DB_PASSWORD="su_contraseña"
    .\mvnw.cmd spring-boot:run |

Si mvnw.cmd falla en Windows, usar el comando equivalente:

    java "-Dmaven.multiModuleProjectDirectory=$PWD" -classpath ".mvn\wrapper\maven-wrapper.jar" org.apache.maven.wrapper.MavenWrapperMain spring-boot:run

Linux / macOS:

    export DB_PASSWORD="su_contraseña"
    ./mvnw spring-boot:run

Está listo cuando aparece `Started GasVolcanApplication` (puerto 8080). Swagger: http://localhost:8080/swagger-ui.html


# Usuarios de demostracion (se crean al iniciar)

| Usuario | Contraseña | Rol 
------------------------------
| admin | Admin123! | ADMIN
| cliente | Cliente123! | CLIENTE

!La creacion de los productos se realiza desde el panel admin del frontend o desde Swagger con el token de admin¡

# Seguridad
- Contraseña guardada con BCrypt.
- JWT firmado, con vencimiento de 60 min.
- 401 sin token valido y 403 cuando el rol esta sin permiso.
- CORS permitido solo para http://localhost:5173

# Regla de negocio
Un pedido no puede superar el stock disponible (responde con 409). Al confirmarse, el stock del producto se descuenta.