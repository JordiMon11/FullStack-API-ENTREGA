# 🌍 Conflict Tracker API

API REST completa para la gestión de conflictos bélicos a nivel mundial, desarrollada con **Spring Boot 3** y **Java 17**.

## 📋 Descripción

Este proyecto implementa una API REST robusta que permite gestionar información sobre conflictos bélicos, incluyendo:
- **Conflictos**: Información detallada sobre conflictos activos, congelados o finalizados
- **Facciones**: Grupos o bandos involucrados en cada conflicto
- **Eventos**: Acontecimientos clave dentro de cada conflicto
- **Países**: Naciones involucradas o que dan soporte a las facciones

La aplicación sigue una **arquitectura por capas** con separación clara de responsabilidades y utiliza **DTOs** para desacoplar el modelo de datos de la API.

## 🏗️ Arquitectura

```
src/main/java/com/example/conflicttracker/
├── controller/          # Controladores REST y Web
│   ├── ConflictController.java
│   ├── FactionController.java
│   ├── EventController.java
│   ├── CountryController.java
│   └── WebController.java
├── service/            # Lógica de negocio
│   ├── ConflictService.java
│   ├── FactionService.java
│   └── EventService.java
├── repository/         # Acceso a datos (JPA)
│   ├── ConflictRepository.java
│   ├── FactionRepository.java
│   ├── EventRepository.java
│   └── CountryRepository.java
├── model/             # Entidades JPA
│   ├── Conflict.java
│   ├── Faction.java
│   ├── Event.java
│   ├── Country.java
│   └── ConflictStatus.java
├── dto/               # Data Transfer Objects
│   ├── ConflictDTO.java
│   ├── ConflictCreateDTO.java
│   ├── ConflictUpdateDTO.java
│   ├── FactionDTO.java
│   ├── FactionCreateDTO.java
│   ├── EventDTO.java
│   ├── EventCreateDTO.java
│   └── CountryDTO.java
└── mapper/            # Conversión Entity ↔ DTO
    ├── ConflictMapper.java
    ├── FactionMapper.java
    └── EventMapper.java
```

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** (Hibernate)
- **Spring Web** (REST API)
- **Spring Boot Thymeleaf** (Frontend)
- **H2 Database** (Desarrollo)
- **PostgreSQL** (Producción)
- **Maven** (Gestión de dependencias)
- **Bootstrap 5** (Estilos del frontend)

## 📦 Requisitos Previos

- **Java 17** o superior
- **Maven 3.6+** o superior
- **PostgreSQL** (opcional, solo para producción)

## ⚙️ Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/JordiMon11/Entrega_actividad_api.git
cd Entrega_actividad_api
```

### 2. Compilar el proyecto

```bash
mvn clean install
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080`

### 4. Acceder a la consola H2 (opcional)

Durante el desarrollo, puedes acceder a la consola de H2:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:conflictdb`
- Username: `sa`
- Password: (dejar vacío)

## 🎯 Uso de la API

### Endpoints de Conflictos

#### Obtener todos los conflictos
```bash
curl http://localhost:8080/api/v1/conflicts
```

#### Filtrar conflictos por estado
```bash
curl http://localhost:8080/api/v1/conflicts?status=ACTIVE
```

#### Obtener un conflicto específico
```bash
curl http://localhost:8080/api/v1/conflicts/1
```

#### Crear un nuevo conflicto
```bash
curl -X POST http://localhost:8080/api/v1/conflicts \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nuevo Conflicto",
    "startDate": "2024-01-01",
    "status": "ACTIVE",
    "description": "Descripción del conflicto",
    "countryIds": [1, 2]
  }'
```

#### Actualizar un conflicto
```bash
curl -X PUT http://localhost:8080/api/v1/conflicts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Conflicto Actualizado",
    "startDate": "2024-01-01",
    "status": "FROZEN",
    "description": "Nueva descripción",
    "countryIds": [1, 2, 3]
  }'
```

#### Eliminar un conflicto
```bash
curl -X DELETE http://localhost:8080/api/v1/conflicts/1
```

#### Obtener conflictos de un país específico
```bash
curl http://localhost:8080/api/v1/countries/UKR/conflicts
```

### Endpoints de Facciones

#### Obtener todas las facciones
```bash
curl http://localhost:8080/api/v1/factions
```

#### Obtener una facción específica
```bash
curl http://localhost:8080/api/v1/factions/1
```

#### Crear una nueva facción
```bash
curl -X POST http://localhost:8080/api/v1/factions \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nueva Facción",
    "conflictId": 1,
    "supporterIds": [1, 5]
  }'
```

#### Actualizar una facción
```bash
curl -X PUT http://localhost:8080/api/v1/factions/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Facción Actualizada",
    "conflictId": 1,
    "supporterIds": [2, 3]
  }'
```

#### Eliminar una facción
```bash
curl -X DELETE http://localhost:8080/api/v1/factions/1
```

### Endpoints de Eventos

#### Obtener todos los eventos
```bash
curl http://localhost:8080/api/v1/events
```

#### Obtener un evento específico
```bash
curl http://localhost:8080/api/v1/events/1
```

#### Crear un nuevo evento
```bash
curl -X POST http://localhost:8080/api/v1/events \
  -H "Content-Type: application/json" \
  -d '{
    "eventDate": "2024-02-15",
    "location": "Ciudad, País",
    "description": "Descripción del evento",
    "conflictId": 1
  }'
```

#### Actualizar un evento
```bash
curl -X PUT http://localhost:8080/api/v1/events/1 \
  -H "Content-Type: application/json" \
  -d '{
    "eventDate": "2024-02-16",
    "location": "Nueva ubicación",
    "description": "Nueva descripción",
    "conflictId": 1
  }'
```

#### Eliminar un evento
```bash
curl -X DELETE http://localhost:8080/api/v1/events/1
```

## 🖥️ Frontend Web

La aplicación incluye una interfaz web desarrollada con Thymeleaf:

### Acceder al frontend
```
http://localhost:8080/web/conflicts
```

### Funcionalidades del frontend:
- ✅ **Lista de conflictos**: Visualiza todos los conflictos en una tabla con sus detalles
- ✅ **Crear conflicto**: Formulario para añadir nuevos conflictos
- ✅ **Eliminar conflicto**: Elimina conflictos existentes
- ✅ **Interfaz responsive**: Diseño adaptativo con Bootstrap 5
- ✅ **Mensajes de feedback**: Alertas de éxito y error

## 🗄️ Modelo de Datos

### Entidades y Relaciones

- **Conflict** ↔ **Country**: ManyToMany (países donde ocurre el conflicto)
- **Faction** → **Conflict**: ManyToOne (cada facción pertenece a un conflicto)
- **Faction** ↔ **Country**: ManyToMany (países que apoyan la facción)
- **Event** → **Conflict**: ManyToOne (cada evento pertenece a un conflicto)

### Estados de Conflicto (Enum)
- `ACTIVE`: Conflicto activo
- `FROZEN`: Conflicto congelado
- `ENDED`: Conflicto finalizado

## 🔧 Configuración para PostgreSQL

Para usar PostgreSQL en producción, modifica `application.properties`:

```properties
# Comentar la configuración de H2 y usar esta:
spring.datasource.url=jdbc:postgresql://localhost:5432/conflictdb
spring.datasource.username=postgres
spring.datasource.password=tu_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

Crear la base de datos:
```sql
CREATE DATABASE conflictdb;
```

## 📊 Datos de Prueba

La aplicación incluye un archivo `data.sql` con datos iniciales:
- 7 países
- 3 conflictos
- 6 facciones
- 6 eventos

## 🧪 Testing con Postman

Importa la siguiente colección en Postman para probar todos los endpoints:

```json
{
  "info": {
    "name": "Conflict Tracker API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Conflicts",
      "item": [
        {
          "name": "Get All Conflicts",
          "request": {
            "method": "GET",
            "url": "http://localhost:8080/api/v1/conflicts"
          }
        },
        {
          "name": "Get Conflict by ID",
          "request": {
            "method": "GET",
            "url": "http://localhost:8080/api/v1/conflicts/1"
          }
        }
      ]
    }
  ]
}
```

# Conflict Tracker API
-----------------------
`Conflict Tracker` es una API REST desarrollada con Spring Boot para la gestión estructurada de información sobre conflictos armados: conflictos, facciones, eventos y países involucrados. El objetivo del proyecto es proporcionar una base extensible y bien documentada para almacenar, consultar y administrar datos relacionados con conflictos a nivel nacional e internacional.

Estado del proyecto
-------------------
- Lenguaje: Java 17
- Framework: Spring Boot 3.2
- Gestión de dependencias: Maven

Principales características
--------------------------
- CRUD completo para `Conflict`, `Faction`, `Event` y `Country`.
- Capas separadas (controller, service, repository) y uso de DTOs y mappers para aislar la capa de persistencia.
- Configuración por defecto para `H2` (desarrollo) y soporte para `PostgreSQL` en producción.
- Interfaz web mínima basada en Thymeleaf para administración básica.

Estructura del proyecto
----------------------
Organización de código relevante (ruta base: `src/main/java`):

- `controller/` — Controladores REST y páginas web.
- `service/` — Lógica de negocio y validaciones.
- `repository/` — Repositorios JPA para acceso a datos.
- `model/` — Entidades JPA y enums.
- `dto/` — Objetos de transferencia entre capas.
- `mapper/` — Conversores entre entidades y DTOs.

Requisitos
----------
- Java 17 o superior.
- Maven 3.6+.
- PostgreSQL (opcional, solo si se despliega en entorno productivo).

Compilación y ejecución
-----------------------
1. Clonar el repositorio:

```bash
git clone <repositorio>
cd <repositorio>
```

2. Construir el artefacto con Maven:

```bash
mvn clean package
```

3. Ejecutar la aplicación en modo desarrollo (usa H2 por defecto):

```bash
mvn spring-boot:run
```

La aplicación se expone por defecto en `http://localhost:8080`.

Configuración de base de datos
------------------------------
Por defecto el proyecto viene configurado para usar H2 en memoria durante el desarrollo. Para usar PostgreSQL en producción, sustituya o extienda las propiedades de `application.properties` o `application.yaml` con la configuración correspondiente:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/conflictdb
spring.datasource.username=postgres
spring.datasource.password=tu_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

Modelado de datos (resumen)
---------------------------
- `Conflict` — Entidad principal. Relación ManyToMany con `Country`.
- `Faction` — Pertenece a un `Conflict` (ManyToOne). Tiene relación ManyToMany con `Country` (países que apoyan).
- `Event` — Pertenece a un `Conflict` (ManyToOne).
- Enum `ConflictStatus` con valores representativos: `ACTIVE`, `FROZEN`, `ENDED`.

Endpoints (resumen)
-------------------
La API expone recursos REST bajo el prefijo `/api/v1/` siguiendo convenciones RESTful.

Rutas principales:

- `GET  /api/v1/conflicts` — Listar conflictos (soporta filtros por estado).
- `GET  /api/v1/conflicts/{id}` — Obtener un conflicto por id.
- `POST /api/v1/conflicts` — Crear un conflicto.
- `PUT  /api/v1/conflicts/{id}` — Actualizar un conflicto.
- `DELETE /api/v1/conflicts/{id}` — Eliminar un conflicto.

- `GET/POST/PUT/DELETE` para `factions` y `events` con rutas análogas.

Ejemplo de uso (crear conflicto) — curl:

```bash
curl -X POST http://localhost:8080/api/v1/conflicts \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nombre del conflicto",
    "startDate": "2024-01-01",
    "status": "ACTIVE",
    "description": "Breve descripción",
    "countryIds": [1, 2]
  }'
```

Datos iniciales y pruebas
------------------------
El proyecto puede incluir un archivo `data.sql` o `import.sql` con datos de ejemplo para poblar la base en tiempo de arranque. Para pruebas manuales, puede utilizarse Postman, curl o la consola H2 (`/h2-console`) en desarrollo.

Testing
-------
El proyecto incorpora dependencias para pruebas (`spring-boot-starter-test`). Añada pruebas unitarias e integradas en `src/test/java` y ejecútelas con:

```bash
mvn test
```

Buenas prácticas y despliegue
----------------------------
- Parametrizar credenciales y URLs a través de variables de entorno o perfiles de Spring (`application-dev.properties`, `application-prod.properties`).
- Evitar `spring.jpa.hibernate.ddl-auto=update` en producción; preferir migraciones controladas (Flyway o Liquibase).
- Habilitar logs y monitoreo para entornos productivos.

Contribuciones
--------------
Las contribuciones son bienvenidas. Para colaborar:

1. Abra un issue describiendo la mejora o corrección.
2. Cree una rama con un nombre descriptivo.
3. Envíe un pull request con una descripción clara y pruebas si procede.

Autor y licencia
-----------------
Proyecto desarrollado como ejercicio académico. Consulte el repositorio para detalles adicionales sobre autoría y licencia.

Contacto
-------
Para consultas técnicas o propuestas de colaboración, abra un issue en el repositorio o contacte al autor listado en el control de versiones.
