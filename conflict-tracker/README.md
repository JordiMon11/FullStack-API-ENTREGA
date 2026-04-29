# FullStack API Entrega — Global Conflict Monitor

## URL pública del Frontend

https://api-fullstack-resultado.vercel.app

## Arquitectura

```
Usuari → Frontend (Vercel) → Backend (Railway) → Base de dades (Supabase/PostgreSQL)
```

- **Frontend:** Vue 3 + Vite → desplegat a Vercel
- **Backend:** Spring Boot (Java) → desplegat a Railway
- **Base de dades:** PostgreSQL → desplegat a Supabase

## Variables d'entorn necessàries

### Backend (Railway)

| Variable | Descripció | Exemple |
|----------|------------|---------|
| `DB_URL` | URL de connexió JDBC a Supabase | `jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:5432/postgres?user=...&password=...&sslmode=require` |
| `DB_USERNAME` | Usuari de la base de dades | `postgres.arbkowpfwnyynmaaseup` |
| `DB_PASSWORD` | Contrasenya de la base de dades | `la_teva_contrasenya` |

### Frontend (Vercel)

| Variable | Descripció | Exemple |
|----------|------------|---------|
| `VITE_API_URL` | URL pública del backend | `https://fullstack-api-entrega-production.up.railway.app` |

## Modificacions realitzades

### 1. Backend — `application.properties`

**Error inicial:** L'aplicació intentava connectar a `localhost:5432` en lloc de Supabase.

```
Connection to localhost:5432 refused. Check that the hostname and port are correct
and that the postmaster is accepting TCP/IP connections.
```

**Solució:** Canviar les credencials hardcoded per variables d'entorn:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

### 2. Backend — `CorsConfig.java` (fitxer nou)

**Error inicial:** El navegador bloquejava les peticions del frontend al backend:

```
Access to fetch at 'https://fullstack-api-entrega-production.up.railway.app/api/v1/conflicts'
from origin 'https://api-fullstack-resultado.vercel.app' has been blocked by CORS policy:
No 'Access-Control-Allow-Origin' header is present on the requested resource.
```

**Solució:** Crear una classe de configuració CORS que permeti peticions des del domini de Vercel:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("https://api-fullstack-resultado.vercel.app")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*");
    }
}
```

---

### 3. Backend — `DB_URL` amb Session Pooler

**Error inicial:** Railway no podia connectar a Supabase perquè usava connexió directa incompatible amb IPv4:

```
java.net.SocketException: Network is unreachable
```

**Solució:** Canviar la `DB_URL` a la variable de Railway per usar el Session Pooler de Supabase, que és compatible amb IPv4:

```
jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:5432/postgres?user=postgres.arbkowpfwnyynmaaseup&password=LA_TEVA_CONTRASENYA&sslmode=require
```

---

### 4. Frontend — `conflicts.js`

**Error inicial:** La URL de l'API estava hardcoded i apuntava a una ruta relativa que no funcionava en producció:

```javascript
const BASE = '/api/v1'
```

**Solució:** Usar la variable d'entorn de Vite per apuntar al backend de Railway:

```javascript
const BASE = `${import.meta.env.VITE_API_URL}/api/v1`
```

---

### 5. Frontend — `vercel.json` (fitxer nou)

**Error:** En refrescar qualsevol ruta interna de Vue (per exemple `/conflictes`) Vercel retornava un error 404 perquè no trobava el fitxer.

**Solució:** Crear el fitxer `vercel.json` a l'arrel del frontend per redirigir totes les rutes a `index.html`:

```json
{
  "rewrites": [{ "source": "/(.*)", "destination": "/" }]
}
```

---

### 6. Frontend — `.env.production` (fitxer nou)

**Error:** En producció el frontend no sabia quina URL usar per connectar amb el backend.

**Solució:** Crear el fitxer `.env.production` i configurar la variable `VITE_API_URL` també al panell de Vercel:

```
VITE_API_URL=https://fullstack-api-entrega-production.up.railway.app
```