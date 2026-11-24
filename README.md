# Plantilla de backend para proyecto EP3 — DSY1104

**Es intencionalmente una plantilla, no una solución de la evaluación.** Incluye JDK 21, Spring Boot 4.1.1, Maven Wrapper, H2, Swagger, validación y un endpoint técnico.

## Inicio
1. Abrir la carpeta en IntelliJ/VS Code.
2. Ejecutar `./mvnw.cmd spring-boot:run` en PowerShell o `mvnw.cmd spring-boot:run` en CMD.
3. Abrir `http://localhost:8080/api/salud` (respuesta de prueba).
4. Abrir `http://localhost:8080/swagger-ui.html`.
5. Renombrar el nombre de proyecto (pom y properties) y registrar los requisitos reales de EP3 según rúbrica oficial antes de implementar funcionalidades.

## Trabajo del grupo
- Hacer inventario de páginas y formularios del frontend EP2.
- Definir entidades, relaciones y validaciones de negocio.
- Diseñar endpoints y respuestas HTTP antes de conectarlos con React.
- Separar `controller`, `service`, `repository`, `model`, y `dto` cuando corresponda.
- Incorporar un módulo a la vez; evitar eliminar de golpe toda la persistencia existente en `localStorage`.

## Comunicación React-Vite
El backend escucha en `http://localhost:8080` y la aplicación Vite suele iniciar en `http://localhost:5173`.
Ejemplo de consumo (en frontend):
```javascript
const respuesta = await fetch('http://localhost:8080/api/salud');
if (!respuesta.ok) throw new Error(`HTTP ${respuesta.status}`);
const resultado = await respuesta.json();
console.log(resultado);
```

## Límites de la plantilla
- `@CrossOrigin` está restringido al origen local Vite para demostración.
- H2 es temporal y en memoria, NO se diseñó para producción.
- No incluye autenticación, autorización, dominio ni endpoints de la evaluación; deben implementarse según los requisitos reales y rúbrica EP3.
- La primera ejecución requiere internet para dependencias y Maven Wrapper.
- Verificar arranque y Swagger en un PC de laboratorio: no fue posible descargar dependencias Maven en el entorno de generación.
