🌍 CONFLICT TRACKER API - GUÍA COMPLETA
📖 ¿DE QUÉ VA ESTE PROYECTO?
Conflict Tracker es una aplicación web que permite gestionar información sobre conflictos bélicos mundiales.
Imagina que eres un analista político y necesitas llevar un registro de:

🌐 Conflictos armados (guerras, disputas territoriales)
⚔️ Facciones o bandos que participan en cada conflicto
📅 Eventos importantes que ocurren en cada conflicto
🏳️ Países involucrados o que dan apoyo

Este proyecto te permite:

✅ Crear, consultar, modificar y eliminar conflictos
✅ Registrar qué países están involucrados
✅ Añadir facciones y sus países de apoyo
✅ Registrar eventos clave (batallas, acuerdos, etc.)
✅ Consultar toda esta información desde una API REST o una interfaz web


🏗️ ¿CÓMO FUNCIONA?
La aplicación tiene DOS interfaces:
1️⃣ API REST (para programadores)
Puedes enviar peticiones HTTP para consultar o modificar datos.
Ejemplo: "Dame todos los conflictos activos"
bashcurl http://localhost:8080/api/v1/conflicts?status=ACTIVE
2️⃣ Interfaz Web (para usuarios)
Una página web visual donde puedes ver y crear conflictos.
Acceso: http://localhost:8080/web/conflicts

La información se organiza así:
CONFLICTO: "Guerra de Ucrania"
├── Fecha de inicio: 24/02/2022
├── Estado: ACTIVO
├── Países involucrados: Ucrania, Rusia
├── Facciones:
│   ├── Fuerzas Armadas Ucranianas (apoyadas por: Ucrania, USA)
│   └── Fuerzas Armadas Rusas (apoyadas por: Rusia)
└── Eventos:
    ├── 24/02/2022 - Kiev - Inicio de la invasión
    └── 03/04/2022 - Bucha - Descubrimiento de víctimas civiles

🚀 CÓMO HACER QUE FUNCIONE
PASO 1: Requisitos Previos
Necesitas tener instalado:

☕ Java 17 (Descargar aquí)
📦 Maven (o usar el que viene con tu IDE)

¿Cómo comprobar que tienes Java?
bashjava -version
Debe mostrar algo como: java version "17.0.x"

PASO 2: Descomprimir el Proyecto

Descomprime el archivo conflict-tracker.zip
Verás una carpeta llamada conflict-tracker/
Esta carpeta contiene TODO el proyecto


PASO 3: Abrir en tu IDE
Opción A: IntelliJ IDEA (Recomendado)

Abre IntelliJ IDEA
File → Open
Selecciona la carpeta conflict-tracker/
Espera a que Maven descargue las dependencias (barra inferior)
✅ Listo

Opción B: Eclipse

Abre Eclipse
File → Import → Maven → Existing Maven Projects
Selecciona la carpeta conflict-tracker/
✅ Listo

Opción C: Visual Studio Code

Abre VS Code
File → Open Folder
Selecciona la carpeta conflict-tracker/
Instala la extensión "Extension Pack for Java" si te la pide
✅ Listo


PASO 4: Compilar el Proyecto
Desde el IDE:

IntelliJ: Click derecho en pom.xml → Maven → Reload Project
Eclipse: Click derecho en proyecto → Maven → Update Project

Desde la terminal:
bashcd conflict-tracker
mvn clean install
Deberías ver al final:
[INFO] BUILD SUCCESS

PASO 5: Ejecutar la Aplicación
Opción A: Desde el IDE

Busca el archivo ConflictTrackerApplication.java
Click derecho → Run 'ConflictTrackerApplication'

Opción B: Desde la terminal
bashmvn spring-boot:run
¿Cómo saber que funciona?
Verás en la consola:
Started ConflictTrackerApplication in X seconds

PASO 6: Probar que Funciona
🌐 Opción 1: Interfaz Web (más fácil)

Abre tu navegador
Ve a: http://localhost:8080/web/conflicts
Deberías ver una tabla con conflictos

Puedes:

Ver la lista de conflictos
Crear un nuevo conflicto (botón "Nuevo Conflicto")
Eliminar conflictos


🔧 Opción 2: API REST (con curl o Postman)
Ver todos los conflictos:
bashcurl http://localhost:8080/api/v1/conflicts
Ver conflictos activos:
bashcurl http://localhost:8080/api/v1/conflicts?status=ACTIVE
Crear un conflicto nuevo:
bashcurl -X POST http://localhost:8080/api/v1/conflicts \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mi Conflicto de Prueba",
    "startDate": "2024-01-15",
    "status": "ACTIVE",
    "description": "Este es un conflicto de prueba",
    "countryIds": [1, 2]
  }'

🗃️ BASE DE DATOS
El proyecto usa H2 (una base de datos en memoria) por defecto.
Ver la base de datos:

Ve a: http://localhost:8080/h2-console
Configura así:

JDBC URL: jdbc:h2:mem:conflictdb
User Name: sa
Password: (déjalo vacío)


Click "Connect"
Verás las tablas: CONFLICT, FACTION, EVENT, COUNTRY

Datos iniciales:
El proyecto viene con datos de ejemplo:

7 países (Ukraine, Russia, Israel, Palestine, USA, Syria, Turkey)
3 conflictos (Guerra de Ucrania, Conflicto Israel-Palestina, Guerra Civil Siria)
6 facciones
6 eventos


📊 ENDPOINTS DISPONIBLES
CONFLICTOS
GET    /api/v1/conflicts              → Lista todos
GET    /api/v1/conflicts?status=ACTIVE → Filtra por estado
GET    /api/v1/conflicts/1            → Obtiene uno específico
POST   /api/v1/conflicts              → Crea uno nuevo
PUT    /api/v1/conflicts/1            → Actualiza uno
DELETE /api/v1/conflicts/1            → Elimina uno
FACCIONES
GET    /api/v1/factions               → Lista todas
GET    /api/v1/factions/1             → Obtiene una específica
POST   /api/v1/factions               → Crea una nueva
PUT    /api/v1/factions/1             → Actualiza una
DELETE /api/v1/factions/1             → Elimina una
EVENTOS
GET    /api/v1/events                 → Lista todos
GET    /api/v1/events/1               → Obtiene uno específico
POST   /api/v1/events                 → Crea uno nuevo
PUT    /api/v1/events/1               → Actualiza uno
DELETE /api/v1/events/1               → Elimina uno
CONSULTAS AVANZADAS
GET    /api/v1/countries/UKR/conflicts → Conflictos de Ucrania

🎯 ESTRUCTURA DEL PROYECTO
conflict-tracker/
│
├── src/main/java/
│   └── com/example/conflicttracker/
│       ├── ConflictTrackerApplication.java  ← Arranca la app
│       │
│       ├── controller/                      ← Reciben peticiones HTTP
│       │   ├── ConflictController.java      (API REST)
│       │   ├── FactionController.java       (API REST)
│       │   ├── EventController.java         (API REST)
│       │   ├── CountryController.java       (API REST)
│       │   └── WebController.java           (Página web)
│       │
│       ├── service/                         ← Lógica de negocio
│       │   ├── ConflictService.java
│       │   ├── FactionService.java
│       │   └── EventService.java
│       │
│       ├── repository/                      ← Acceso a base de datos
│       │   ├── ConflictRepository.java
│       │   ├── FactionRepository.java
│       │   ├── EventRepository.java
│       │   └── CountryRepository.java
│       │
│       ├── model/                           ← Tablas de la BD
│       │   ├── Conflict.java
│       │   ├── Faction.java
│       │   ├── Event.java
│       │   ├── Country.java
│       │   └── ConflictStatus.java
│       │
│       ├── dto/                             ← Objetos de transferencia
│       │   └── (8 archivos)
│       │
│       └── mapper/                          ← Conversores
│           └── (3 archivos)
│
├── src/main/resources/
│   ├── templates/                           ← Páginas HTML
│   │   ├── conflicts.html                   (Lista)
│   │   └── conflict-form.html               (Formulario)
│   ├── application.properties               ← Configuración
│   └── data.sql                             ← Datos iniciales
│
├── pom.xml                                  ← Dependencias Maven
└── README.md                                ← Esta documentación

🐛 SOLUCIÓN DE PROBLEMAS
❌ Error: "Could not find or load main class"
Solución:
bashmvn clean install
❌ Error: "Port 8080 already in use"
Solución: Cambia el puerto en application.properties:
propertiesserver.port=8081
❌ Error: "Failed to configure a DataSource"
Solución: Verifica que application.properties tenga:
propertiesspring.datasource.url=jdbc:h2:mem:conflictdb
❌ La página web no carga (404)
Verifica: Que Thymeleaf esté en el pom.xml:
xml<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

📝 EJEMPLOS DE USO
Ejemplo 1: Ver todos los conflictos activos
Desde navegador:
http://localhost:8080/api/v1/conflicts?status=ACTIVE
Desde terminal:
bashcurl http://localhost:8080/api/v1/conflicts?status=ACTIVE
Ejemplo 2: Crear un evento
bashcurl -X POST http://localhost:8080/api/v1/events \
  -H "Content-Type: application/json" \
  -d '{
    "eventDate": "2024-02-16",
    "location": "Madrid, España",
    "description": "Cumbre de paz",
    "conflictId": 1
  }'
Ejemplo 3: Ver conflictos de un país
bashcurl http://localhost:8080/api/v1/countries/UKR/conflicts

🎓 PARA ESTUDIANTES
Este proyecto cumple con:

✅ Arquitectura por capas (Controller → Service → Repository)
✅ DTOs para desacoplar API del modelo
✅ Mappers para conversión Entity ↔ DTO
✅ CRUD completo para todas las entidades
✅ Frontend con Thymeleaf
✅ Base de datos relacional (JPA)
✅ Endpoints REST bien diseñados


📞 ¿NECESITAS AYUDA?

Revisa los logs en la consola donde ejecutaste la aplicación
Accede a H2 para ver la base de datos
Prueba primero la interfaz web (es más visual)
Luego prueba la API con curl o Postman


✅ CHECKLIST RÁPIDO

 Tengo Java 17 instalado
 He descomprimido el proyecto
 He abierto la carpeta en mi IDE
 He ejecutado mvn clean install
 He ejecutado la aplicación
 Puedo acceder a http://localhost:8080/web/conflicts
 Puedo acceder a http://localhost:8080/h2-console
 Los endpoints de la API funcionan

Si todos están marcados: ¡FUNCIONA! 🎉

¡Buena suerte con tu proyecto! 🚀
