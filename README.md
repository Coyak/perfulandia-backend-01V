# 🧾 Proyecto: Transformación Digital - Perfulandia SPA

Este repositorio contiene el desarrollo técnico del sistema basado en microservicios para la empresa Perfulandia SPA, como parte de la Evaluación Parcial 2 de la asignatura **Desarrollo Full Stack I**.

## 📦 Descripción General del Proyecto

> 📝 Perfulandia SPA, una empresa chilena de perfumería en crecimiento, enfrenta problemas de lentitud, baja escalabilidad y errores debido a su sistema monolítico actual. Este proyecto propone una transformación digital hacia una arquitectura de microservicios para superar estas limitaciones.
> El nuevo sistema resolverá fallas críticas como caídas en horas punta y ventas duplicadas


## 🧩 Arquitectura de Microservicios

> 📝 Describir cómo está estructurado el sistema en microservicios. Pueden incluir un diagrama y explicar brevemente la función de cada servicio.

### Microservicios Desarrollados

- `usuarioservice`: > 📝 Gestiona el registro, autenticación y autorización de los usuarios.
- `productoservice`: > 📝 Maneja la información completa de los productos (descripción, precio, stock, categorías).
- `emailserviceservice`: > 📝 Se encarga exclusivamente del envío de notificaciones por correo electrónico.

## 🛠️ Tecnologías Utilizadas

> 📝 * **Spring Boot:**
>     * **Maven:**
>     * **MySQL:**
>     * **Postman:**
>     * **GitHub:**

## 🗄️ Configuración de Bases de Datos

> 📝 Indicar qué motor de base de datos usaron, cómo configuraron la conexión (`application.properties`), y qué tablas y campos definieron para cada microservicio.

## 📮 Endpoints y Pruebas

> 📝 Especificar los principales endpoints disponibles por microservicio (CRUD y llamadas entre servicios).  
> Incluir capturas o descripciones de pruebas realizadas con Postman (mínimo 3 por micro-servicio).

## 🧑‍💻 Integrantes del Equipo

> 📝 Indicar nombre completo y rol de cada integrante del equipo.

| Nombre                  | Rol en el proyecto         | Servicio principal trabajado |
|-------------------------|----------------------------|------------------------------|
| Angel Bustamante        | Repositorio                | emailservice                 |
| Miguel Muñoz            | Productos                  | productoservice              |
| Ismael Oyarzun          | usuarios                   | Usuarioservice               |

## 📂 Estructura del Repositorio

> 📝 Explicar brevemente la organización de carpetas del repositorio (por ejemplo, cada carpeta corresponde a un microservicio separado con su propio `pom.xml`).

```

📦 perfulandia-microservices
├── usuarioservice
├── productoservice
├── emailservice
└── README.md

```

## 👥 Colaboración en GitHub

> 📝 Explicar cómo se organizó el trabajo en ramas (`master`, `pruebas`), frecuencia de commits y cómo se coordinaron como equipo.

## 📈 Lecciones Aprendidas

> 📝 Aprendimos la leccion

---

[Guía Oficial en Notion – Evaluación Parcial 2 (35%)](https://quilt-canary-969.notion.site/Gu-a-Oficial-Evaluaci-n-Parcial-2-35-1f75b3c4e31280aaab79c9a71f1cfb7b?pvs=4)

