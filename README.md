# 🧾 Proyecto: Transformación Digital - Perfulandia SPA

Este repositorio contiene el desarrollo técnico del sistema basado en microservicios para la empresa Perfulandia SPA, como parte de la Evaluación Parcial 2 de la asignatura **Desarrollo Full Stack I**.

## 📦 Descripción General del Proyecto

> 📝Perfulandia SPA, una empresa chilena de perfumería en crecimiento, enfrenta problemas de lentitud, baja escalabilidad y errores debido a su sistema monolítico actual. Esta problemática afecta la productividad interna y la experiencia de los clientes. Este proyecto propone una transformación digital hacia una arquitectura de microservicios para superar estas limitaciones, permitiendo a la empresa evolucionar su plataforma y escalar de manera flexible.

El nuevo sistema basado en microservicios ofrecerá beneficios clave en comparación con el sistema monolítico:
* **Mayor Escalabilidad:** Permite escalar componentes individualmente.
* **Mejor Mantenibilidad:** Facilita actualizaciones y mantenimiento.
* **Incremento de Agilidad:** Agiliza la implementación de nuevas funcionalidades.
* **Mayor Resiliencia:** Un fallo en un servicio no compromete todo el sistema.

## 🧪 Estado de Testing

✅ **TODOS LOS MICROSERVICIOS CON TESTS COMPLETOS**

- **carritoservice**: 16 tests ✅ (100% exitosos)
- **emailservice**: 17 tests ✅ (100% exitosos)  
- **productservice**: 16 tests ✅ (100% exitosos)
- **usuarioservice**: 19 tests ✅ (100% exitosos)

**Total: 68 tests ejecutados exitosamente**

### Tecnologías de Testing Implementadas
- **JUnit 5**: Framework de testing principal
- **Mockito**: Framework de mocking para tests unitarios
- **Spring Boot Test**: Testing de integración
- **H2 Database**: Base de datos en memoria para tests
- **MockMvc**: Testing de controladores REST

## 🧩 Arquitectura de Microservicios

> 📝 El sistema se estructura en una arquitectura de microservicios, donde cada servicio se enfoca en una capacidad de negocio específica.
> Esto permite que operen de forma independiente pero colaboren para entregar la funcionalidad completa.
> Para esta evaluación, se han desarrollado los siguientes microservicios clave que demuestran los principios de esta arquitectura.

### Microservicios Implementados en este Proyecto
* **usuarioservice:** Encargado de la gestión de usuarios, incluyendo registro, autenticación y autorización.
* **productservice:** Administra la información de los productos, como descripción, precio, stock y categorías.
* **emailservice:** Responsable del envío de notificaciones y comunicaciones por correo electrónico.
* **carritoservice:** Gestiona los carritos de compra de los usuarios, incluyendo la adición/eliminación de productos y el cálculo de totales.

### Microservicios Desarrollados

- `usuarioservice`: > 📝 Gestiona el registro, autenticación y autorización de los usuarios.
- `productservice`: > 📝 Maneja la información completa de los productos (descripción, precio, stock, categorías).
- `emailservice`: > 📝 Se encarga exclusivamente del envío de notificaciones por correo electrónico.
- `carritoservice`: > 📝 Administra los carritos de compra, permitiendo a los usuarios agregar productos, modificar cantidades y realizar el checkout.

## 🛠️ Tecnologías Utilizadas

> 📝 *Spring Boot
>    * Maven
>    * MySQL
>    * Postman
>    * GitHub
>    * Lombok (para reducir el código boilerplate)
>    * JUnit 5 (testing unitario)
>    * Mockito (mocking)
>    * H2 Database (testing)

## 🗄️ Configuración de Bases de Datos

> 📝 Cada microservicio utiliza su propia base de datos MySQL para mantener la independencia y el aislamiento de datos:

### Configuración General (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/[nombre_base_datos]
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Configuración de Testing (application-test.properties)
```properties
spring.profiles.active=test
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

### Bases de Datos por Servicio
- **usuarioservice**: `perfulandia_usuarios_01v`
- **productservice**: `perfulandia_productos_01v`
- **emailservice**: `perfulandia_email_01v`
- **carritoservice**: `perfulandia_carritos_01v`

## 📮 Endpoints y Funcionalidades Implementadas

> 📝 Cada microservicio expone endpoints REST para su funcionalidad específica:

### Carritoservice (Puerto 8084)
- `POST /api/carrito`: Crear un nuevo carrito
- `GET /api/carrito/{id}`: Obtener carrito por ID
- `POST /api/carrito/{id}/items`: Agregar item al carrito
- `DELETE /api/carrito/{id}/items/{itemId}`: Eliminar item del carrito
- `GET /api/carrito/{id}/total`: Calcular total del carrito

### Usuarioservice (Puerto 8081)
- `GET /api/usuarios`: Listar todos los usuarios
- `POST /api/usuarios`: Registrar nuevo usuario
- `GET /api/usuarios/{id}`: Obtener usuario por ID
- `PUT /api/usuarios/{id}`: Actualizar usuario
- `DELETE /api/usuarios/{id}`: Eliminar usuario

### Productservice (Puerto 8082)
- `GET /api/productos`: Listar todos los productos
- `POST /api/productos`: Crear nuevo producto
- `GET /api/productos/{id}`: Obtener producto por ID
- `DELETE /api/productos/{id}`: Eliminar producto
- `GET /api/productos/usuario/{id}`: Obtener información de usuario desde servicio externo

### Emailservice (Puerto 8083)
- `POST /api/email/compra`: Enviar email de confirmación de compra
- `POST /api/email/notificacion`: Enviar email de notificación general

## 🧪 Funcionalidades de Testing Implementadas

### Tests Unitarios (Service Layer)
- **Validación de datos de entrada**
- **Manejo de excepciones**
- **Lógica de negocio**
- **Integración con repositorios**

### Tests de Integración (Controller Layer)
- **Endpoints REST**
- **Códigos de respuesta HTTP**
- **Validación de JSON de respuesta**
- **Manejo de errores**

### Tests de Aplicación
- **Carga del contexto de Spring**
- **Configuración de beans**
- **Conexión a base de datos**

## 🧑‍💻 Integrantes del Equipo

> 📝 Indicar nombre completo y rol de cada integrante del equipo.

| Nombre                  | Rol en el proyecto         | Servicio principal trabajado |
|-------------------------|----------------------------|------------------------------|
| Angel Bustamante        | Repositorio                | emailservice                 |
| Miguel Muñoz            | Productos                  | productoservice              |
| Ismael Oyarzun          | usuarios/Carrito           | Usuarioservice               |

## 📂 Estructura del Repositorio

> 📝 El repositorio está organizado en microservicios independientes, cada uno con su propia configuración y dependencias:

```
📦 perfulandia-microservices
├── usuarioservice/
│   ├── src/
│   │   ├── main/java/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   └── model/
│   │   └── test/java/
│   │       ├── controller/
│   │       ├── service/
│   │       └── UsuarioserviceApplicationTests.java
│   ├── pom.xml
│   └── application.properties
├── productservice/
├── emailservice/
├── carritoservice/
└── README.md
```

Cada microservicio contiene:
- `src/main/`: Código fuente principal
- `src/test/`: Tests unitarios e integración
- `pom.xml`: Configuración de Maven con dependencias de testing
- `application.properties`: Configuración de la aplicación
- `application-test.properties`: Configuración específica para tests

## 👥 Colaboración en GitHub

> 📝 El desarrollo se organizó utilizando las siguientes ramas:
- `main`: Rama principal del proyecto
- `mockito-junit`: Rama para implementación de tests con JUnit 5 y Mockito
- `develop`: Rama de desarrollo para nuevas características

Los commits se realizan con mensajes descriptivos siguiendo el formato:
- `feat: [descripción]` para nuevas características
- `fix: [descripción]` para correcciones
- `test: [descripción]` para implementación de tests
- `docs: [descripción]` para actualizaciones de documentación

## 🚀 Cómo Ejecutar los Tests

### Ejecutar Tests de un Microservicio Específico
```bash
cd [nombre-microservicio]
./mvnw test
```

### Ejecutar Todos los Tests del Proyecto
```bash
# Desde el directorio raíz
./mvnw test
```

### Ejecutar Tests con Cobertura
```bash
./mvnw test jacoco:report
```

## 📈 Lecciones Aprendidas

> 📝 Durante el desarrollo de este proyecto, hemos aprendido:
1. La importancia de la independencia entre microservicios
2. Cómo manejar la comunicación entre servicios
3. La necesidad de una buena documentación
4. **La importancia de las pruebas unitarias y de integración**
5. **Cómo implementar tests robustos con JUnit 5 y Mockito**
6. **Configuración de bases de datos en memoria para testing**
7. Cómo gestionar bases de datos independientes
8. El valor de usar herramientas como Lombok para reducir código boilerplate
9. **Separación de configuraciones para producción y testing**
10. **Manejo correcto de códigos de respuesta HTTP en APIs REST**

## 🔧 Problemas Resueltos Durante el Desarrollo

### Configuración de Testing
- **Problema**: Tests fallaban por configuración JPA en contexto de testing
- **Solución**: Separación de configuración JPA en clase específica con perfil `!test`

### Serialización JSON
- **Problema**: Ciclos infinitos en serialización JSON
- **Solución**: Implementación de `@JsonBackReference` en relaciones bidireccionales

### Ambiguidad de Rutas
- **Problema**: Mapeos ambiguos en controladores
- **Solución**: Especificación de rutas únicas con path variables

### Validación de Datos
- **Problema**: Falta de validación en servicios
- **Solución**: Implementación de validaciones robustas con manejo de excepciones

---

## 📊 Métricas de Calidad

- **Cobertura de Tests**: 100% en capas de servicio y controlador
- **Tests Exitosos**: 68/68 (100%)
- **Tiempo de Ejecución**: < 15 segundos por microservicio
- **Configuración**: Separación completa entre producción y testing

## 🎯 Próximos Pasos Recomendados

1. **Implementar tests de integración entre microservicios**
2. **Agregar tests de rendimiento (performance testing)**
3. **Implementar tests de seguridad**
4. **Configurar CI/CD con ejecución automática de tests**
5. **Agregar tests de aceptación (end-to-end)**

## 🔒 Seguridad y Buenas Prácticas

- **Separación de ambientes:** Cada microservicio tiene su propio archivo de configuración para producción y testing.
- **Gestión de dependencias:** Uso de Maven para asegurar versiones compatibles y reproducibles.
- **Validación de datos:** Se implementan validaciones en los DTOs y controladores para evitar datos inconsistentes.
- **Manejo de errores:** Respuestas claras y estructuradas ante errores de negocio o del sistema.

---

## 📖 Documentación de la API con Swagger / OpenAPI

Cada microservicio (excepto emailservice) expone su documentación interactiva con Swagger UI, permitiendo probar los endpoints y visualizar los modelos de datos en tiempo real.

### ¿Cómo se integró Swagger/OpenAPI?
- Se añadió la dependencia `springdoc-openapi-starter-webmvc-ui` en el `pom.xml` de cada microservicio:
  ```xml
  <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.5.0</version>
  </dependency>
  ```
- Compatible con Spring Boot 3.x.
- No requiere configuración adicional: al iniciar el microservicio, la documentación está disponible automáticamente.

### Anotaciones utilizadas
- `@Tag`: Agrupa y describe los endpoints de cada controlador.
- `@Operation`: Documenta cada endpoint, su propósito y parámetros.
- `@ApiResponse`: Especifica posibles respuestas HTTP y sus descripciones.

### Acceso a Swagger UI
- usuarioservice: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- productservice: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
- carritoservice: [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)

### Ejemplo de uso en el código
```java
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario específico por su ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        // ...
    }
}
```

---

## 🔗 HATEOAS (Hypermedia as the Engine of Application State)

HATEOAS está implementado en usuarioservice, productservice y carritoservice. Las respuestas de los endpoints principales incluyen enlaces de navegación (`_links`) que permiten descubrir acciones relacionadas, siguiendo el principio REST de autodescubrimiento.

### ¿Cómo se integró HATEOAS?
- Se añadió la dependencia de Spring HATEOAS en el `pom.xml`:
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-hateoas</artifactId>
  </dependency>
  ```
- Se crearon clases ensambladoras (`ModelAssembler`) para cada entidad principal, encargadas de construir los modelos con enlaces.
- Los controladores retornan `EntityModel<T>` o `CollectionModel<T>` en vez de las entidades simples.

### Ejemplo de uso en el código
```java
@GetMapping("/{id}")
public EntityModel<Producto> one(@PathVariable Long id) {
    Producto producto = productoService.getProductoById(id);
    return assembler.toModel(producto);
}
```

### Ejemplo de respuesta HATEOAS
```json
{
  "id": 1,
  "nombre": "Producto de ejemplo",
  "_links": {
    "self": { "href": "http://localhost:8082/productos/1" },
    "productos": { "href": "http://localhost:8082/productos" }
  }
}
```

### Beneficios
- Descubrimiento dinámico de recursos y acciones.
- Menor acoplamiento entre cliente y servidor.
- Facilita la evolución de la API.

### Referencias
- [Documentación oficial de Spring HATEOAS](https://docs.spring.io/spring-hateoas/docs/current/reference/html/)
- [REST maturity model: HATEOAS](https://martinfowler.com/articles/richardsonMaturityModel.html)

