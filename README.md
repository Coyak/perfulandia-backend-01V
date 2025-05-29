# 🧾 Proyecto: Transformación Digital - Perfulandia SPA

Este repositorio contiene el desarrollo técnico del sistema basado en microservicios para la empresa Perfulandia SPA, como parte de la Evaluación Parcial 2 de la asignatura **Desarrollo Full Stack I**.

## 📦 Descripción General del Proyecto

> 📝Perfulandia SPA, una empresa chilena de perfumería en crecimiento, enfrenta problemas de lentitud, baja escalabilidad y errores debido a su sistema monolítico actual. Esta problemática afecta la productividad interna y la experiencia de los clientes. Este proyecto propone una transformación digital hacia una arquitectura de microservicios para superar estas limitaciones, permitiendo a la empresa evolucionar su plataforma y escalar de manera flexible.

El nuevo sistema basado en microservicios ofrecerá beneficios clave en comparación con el sistema monolítico:
* **Mayor Escalabilidad:** Permite escalar componentes individualmente.
* **Mejor Mantenibilidad:** Facilita actualizaciones y mantenimiento.
* **Incremento de Agilidad:** Agiliza la implementación de nuevas funcionalidades.
* **Mayor Resiliencia:** Un fallo en un servicio no compromete todo el sistema.


## 🧩 Arquitectura de Microservicios

> 📝 El sistema se estructura en una arquitectura de microservicios, donde cada servicio se enfoca en una capacidad de negocio específica.
> Esto permite que operen de forma independiente pero colaboren para entregar la funcionalidad completa.
> Para esta evaluación, se han desarrollado los siguientes microservicios clave que demuestran los principios de esta arquitectura.

### Microservicios Implementados en este Proyecto
* **usuarioservice:** Encargado de la gestión de usuarios, incluyendo registro, autenticación y autorización.
* **productoservice:** Administra la información de los productos, como descripción, precio, stock y categorías.
* **emailserviceservice:** Responsable del envío de notificaciones y comunicaciones por correo electrónico.
* **carritoservice:** Gestiona los carritos de compra de los usuarios, incluyendo la adición/eliminación de productos y el cálculo de totales.

### Microservicios Desarrollados

- `usuarioservice`: > 📝 Gestiona el registro, autenticación y autorización de los usuarios.
- `productoservice`: > 📝 Maneja la información completa de los productos (descripción, precio, stock, categorías).
- `emailserviceservice`: > 📝 Se encarga exclusivamente del envío de notificaciones por correo electrónico.
- `carritoservice`: > 📝 Administra los carritos de compra, permitiendo a los usuarios agregar productos, modificar cantidades y realizar el checkout.

## 🛠️ Tecnologías Utilizadas

> 📝 *Spring Boot
>    * Maven
>    * MySQL
>    * Postman
>    * GitHub
>    * Lombok (para reducir el código boilerplate)

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

### Bases de Datos por Servicio
- **usuarioservice**: `perfulandia_usuarios_01v`
- **productoservice**: `perfulandia_productos_01v`
- **emailservice**: `perfulandia_email_01v`
- **carritoservice**: `perfulandia_carritos_01v`

## 📮 Endpoints y Pruebas

> 📝 Cada microservicio expone endpoints REST para su funcionalidad específica:

### Carritoservice (Puerto 8084)
- `POST /api/carrito`: Crear un nuevo carrito
- `GET /api/carrito/{id}`: Obtener carrito por ID
- `POST /api/carrito/{id}/items`: Agregar item al carrito
- `DELETE /api/carrito/{id}/items/{itemId}`: Eliminar item del carrito
- `GET /api/carrito/{id}/total`: Calcular total del carrito

### Usuarioservice (Puerto 8081)
- `POST /api/usuarios`: Registrar nuevo usuario
- `GET /api/usuarios/{id}`: Obtener usuario por ID
- `PUT /api/usuarios/{id}`: Actualizar usuario

### Productoservice (Puerto 8082)
- `GET /api/productos`: Listar todos los productos
- `GET /api/productos/{id}`: Obtener producto por ID
- `POST /api/productos`: Crear nuevo producto

### Emailservice (Puerto 8083)
- `POST /api/email`: Enviar correo electrónico
- `GET /api/email/status`: Verificar estado del servicio

## 🧑‍💻 Integrantes del Equipo

> 📝 Indicar nombre completo y rol de cada integrante del equipo.

| Nombre                  | Rol en el proyecto         | Servicio principal trabajado |
|-------------------------|----------------------------|------------------------------|
| Angel Bustamante        | Repositorio                | emailservice                 |
| Miguel Muñoz            | Productos                  | productoservice              |
| Ismael Oyarzun          | usuarios                   | Usuarioservice               |

## 📂 Estructura del Repositorio

> 📝 El repositorio está organizado en microservicios independientes, cada uno con su propia configuración y dependencias:

```
📦 perfulandia-microservices
├── usuarioservice
├── productoservice
├── emailservice
├── carritoservice
└── README.md
```

Cada microservicio contiene:
- `src/`: Código fuente
- `pom.xml`: Configuración de Maven
- `application.properties`: Configuración de la aplicación

## 👥 Colaboración en GitHub

> 📝 El desarrollo se organizó utilizando las siguientes ramas:
- `main`: Rama principal del proyecto
- `testCarrito`: Rama para el desarrollo del servicio de carrito
- `develop`: Rama de desarrollo para nuevas características

Los commits se realizan con mensajes descriptivos siguiendo el formato:
- `feat: [descripción]` para nuevas características
- `fix: [descripción]` para correcciones
- `docs: [descripción]` para actualizaciones de documentación

## 📈 Lecciones Aprendidas

> 📝 Durante el desarrollo de este proyecto, hemos aprendido:
1. La importancia de la independencia entre microservicios
2. Cómo manejar la comunicación entre servicios
3. La necesidad de una buena documentación
4. La importancia de las pruebas unitarias
5. Cómo gestionar bases de datos independientes
6. El valor de usar herramientas como Lombok para reducir código boilerplate

---

[Guía Oficial en Notion – Evaluación Parcial 2 (35%)](https://quilt-canary-969.notion.site/Gu-a-Oficial-Evaluaci-n-Parcial-2-35-1f75b3c4e31280aaab79c9a71f1cfb7b?pvs=4)

