# Configuración de CarritoService con Laragon

## Requisitos Previos

1. **Laragon instalado** (versión 5.x o superior)
2. **Java 17** o superior
3. **Maven** (incluido en el proyecto como wrapper)

## Pasos de Configuración

### 1. Iniciar Laragon

1. Abrir Laragon
2. Hacer clic en "Start All" para iniciar Apache y MySQL
3. Verificar que MySQL esté corriendo en el puerto 3306

### 2. Crear la Base de Datos

**Opción A: Usando phpMyAdmin**
1. Abrir Laragon
2. Hacer clic en "Database" → "phpMyAdmin"
3. Ejecutar el script SQL: `database_setup.sql`

**Opción B: Usando HeidiSQL**
1. Abrir Laragon
2. Hacer clic en "Database" → "HeidiSQL"
3. Conectar a MySQL (localhost, puerto 3306, usuario: root, sin contraseña)
4. Ejecutar el script SQL: `database_setup.sql`

### 3. Configurar el Proyecto

El archivo `application.properties` ya está configurado para Laragon con:
- URL: `jdbc:mysql://localhost:3306/perfulandia_carritos_01v`
- Usuario: `root`
- Contraseña: (vacía)
- Puerto: `8084`

### 4. Ejecutar el Proyecto

```bash
# Desde la carpeta carritoservice
./mvnw spring-boot:run
```

### 5. Verificar la Aplicación

- **URL de la aplicación**: http://localhost:8084
- **Endpoints disponibles**:
  - GET `/api/carritos` - Listar carritos
  - POST `/api/carritos` - Crear carrito
  - GET `/api/carritos/{id}` - Obtener carrito por ID
  - PUT `/api/carritos/{id}` - Actualizar carrito
  - DELETE `/api/carritos/{id}` - Eliminar carrito

### 6. Ejecutar Tests

```bash
# Tests unitarios (usando H2 en memoria)
./mvnw test

# Tests de integración (requiere MySQL corriendo)
./mvnw test -Dspring.profiles.active=integration
```

## Configuración de Puertos

- **MySQL**: 3306 (por defecto en Laragon)
- **CarritoService**: 8084
- **phpMyAdmin**: 8080 (si está habilitado)

## Solución de Problemas

### Error de Conexión a MySQL
1. Verificar que Laragon esté corriendo
2. Verificar que MySQL esté iniciado
3. Verificar credenciales en `application.properties`

### Puerto Ocupado
Si el puerto 8084 está ocupado, cambiar en `application.properties`:
```properties
server.port=8085
```

### Problemas con JPA/Hibernate
1. Verificar que la base de datos existe
2. Verificar que el usuario tenga permisos
3. Revisar logs de la aplicación

## Estructura de Base de Datos

Las tablas se crean automáticamente con JPA:
- `carrito` - Tabla principal de carritos
- `item_carrito` - Tabla de items en carritos

## Comandos Útiles

```bash
# Limpiar y compilar
./mvnw clean compile

# Ejecutar tests
./mvnw test

# Ejecutar aplicación
./mvnw spring-boot:run

# Crear JAR ejecutable
./mvnw package
``` 