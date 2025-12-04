# 🏡 Home360: Tu casa a un click

Bienvenido a **Home360**, una plataforma inmobiliaria digital construida con una arquitectura de **microservicios**, bajo principios de **DDD (Domain-Driven Design)** y una estructura **Hexagonal (Puertos y Adaptadores)** que prioriza modularidad, escalabilidad y mantenibilidad.

---

## 🧭 Resumen General

**Home360** es una solución integral para la gestión de propiedades inmobiliarias. El sistema se compone de microservicios especializados, cada uno encargado de un subdominio específico:

* **Gestión de Usuarios y Autenticación**
* **Administración de Propiedades**
* **Gestión de Visitas (en desarrollo)**

Toda la comunicación desde el cliente pasa por un **API Gateway**, y los servicios se registran y descubren dinámicamente mediante **Netflix Eureka**.
La persistencia se maneja en **MySQL**, con un esquema por microservicio.

---

## 🏗️ Arquitectura General

```
+-----------+      +---------------------+      +-------------------------+      +---------------------+      +-----------------+
|  Usuario  |----->|     API Gateway     |----->|   Service Discovery     |<---->|    Microservicio    |----->| Base de Datos   |
| (Cliente) |      | (service-gateway)   |      |   (service-discovery)   |      | (ej: service-home)  |      |   (MySQL)       |
+-----------+      +---------------------+      +-------------------------+      +---------------------+      +-----------------+
       ^                          |                                                           |                        |
       |                          |                                                           |                        |
       +--------------------------+-----------------------Respuesta----------------------------+------------------------+
```

---

## ⚙️ Descripción de Microservicios

### 🚪 API Gateway – `service-gateway`

* **Tecnología:** Spring Cloud Gateway MVC
* **Funciones:** Punto único de entrada, validación de JWT, enrutamiento, exposición de Swagger UI
* **Puerto:** 8080

### 🧭 Service Discovery – `service-discovery`

* **Tecnología:** Netflix Eureka
* **Funciones:** Registro y descubrimiento dinámico de servicios
* **Puerto:** 8761

### 👤 Microservicio de Usuarios – `service-user`

* **Funciones:** Gestión de usuarios, autenticación, emisión de JWT
* **Base de Datos:** `services_user`
* **Puerto:** 8081

### 🏠 Microservicio de Propiedades – `service-home`

* **Funciones:** CRUD de propiedades, categorías, ubicaciones, gestión de imágenes, tareas programadas
* **Base de Datos:** `services_home`
* **Puerto:** 8082

### 🚶 Microservicio de Visitas – `service-visits`

* **Funciones:** Gestión de visitas a propiedades (fase inicial)
* **Base de Datos:** `services_visits`
* **Puerto:** configurable (ej: 8083)

### 🗃️ Base de Datos – `mysql-db`

* **Tecnología:** MySQL 8
* **Configuración:** esquemas independientes por microservicio
* **Puerto host:** 3306

### 🛠️ Herramientas adicionales

* **phpMyAdmin:** Administración MySQL – Puerto 8088
* **service-config:** Config Server (uso no obligatorio) – Puerto 8085

---

## 💻 Stack Tecnológico

* **Java 17**, Spring Boot 3.x
* Spring Data JPA, Spring Security (OAuth2 Resource Server)
* Spring Cloud Gateway, Eureka
* **JWT** con nimbus-jose-jwt
* **MySQL** + Docker/Docker Compose
* **Gradle**
* **Swagger/OpenAPI 3**
* **MapStruct**

---

## 🔑 Flujo de Autenticación (JWT)

1. Cliente envía credenciales → `POST /api/v1/auth/sign-in`
2. `service-user` valida y genera el JWT
3. Cliente envía solicitudes con `Authorization: Bearer <token>`
4. API Gateway valida firma y expiración
5. Gateway inyecta cabeceras:

   * `X-User-Id`
   * `X-User-Roles`
6. Microservicios internos realizan autorización según roles/identidad.

---

## 🌊 Flujo Típico de Solicitud

1. Cliente envía una petición al Gateway
2. Gateway enruta según configuración
3. Valida JWT (si aplica)
4. Añade cabeceras de identidad
5. Consulta Eureka para obtener instancia del servicio destino
6. Envia la solicitud al microservicio correspondiente
7. El microservicio ejecuta lógica de dominio
8. Interacción con MySQL mediante JPA
9. Genera respuesta y la devuelve al Gateway
10. Gateway la retorna al cliente

---

## 📂 Estructura del Repositorio

```
├── compose.yml              
├── init-db.sql             
├── README.md               
├── service-config/         
├── service-discovery/      
├── service-gateway/        
├── service-home/           
│   ├── src/main/java/com/pragma/home360/home/
│   │   ├── application/
│   │   ├── domain/
│   │   └── infrastructure/
│   ├── build.gradle
└── service-visits/
```

---

## 🚀 Guía de Inicio Rápido

**Requisitos:**

* Java 17
* Docker + Docker Compose
* MySQL o contenedor equivalente

**Pasos:**

1. Clonar el repositorio
2. Construir cada microservicio con Gradle
3. Levantar la infraestructura con Docker Compose
4. Ejecutar microservicios en el orden recomendado:

   1. Service Discovery
   2. API Gateway
   3. service-user
   4. service-home
   5. service-visits

---

## 🌐 Endpoints de Verificación

* **Eureka:**
  `http://localhost:8761`

* **Swagger consolidado vía Gateway:**
  `http://localhost:8080/swagger-ui.html`

* **phpMyAdmin (Base de datos):**
  `http://localhost:8088`
