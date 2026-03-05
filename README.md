# ForoHub API

API REST desarrollada con **Spring Boot 3 + Spring Security + JWT + MySQL** que permite gestionar un foro con autenticación segura, roles y control de acceso.

---

##  Características

*  Autenticación con JWT
*  Registro y Login de usuarios
*  Gestión de Cursos
*  CRUD completo de Tópicos
*  CRUD completo de Respuestas
*  Control de acceso con Spring Security
*  Documentación automática con Swagger
*  Persistencia con JPA + Hibernate
*  Encriptación de contraseñas con BCrypt

---

##  Tecnologías utilizadas

* Java 17+
* Spring Boot 3
* Spring Security
* JWT (jjwt 0.11.5)
* MySQL
* JPA / Hibernate
* Maven
* Swagger (Springdoc OpenAPI)

---

##  Estructura del proyecto

```
com.forohub
│
├── config
│   ├── SecurityConfig
│   └── OpenApiConfig
│
├── controller
│   ├── AuthController
│   ├── TopicoController
│   └── RespuestaController
│
├── dto
│
├── entity
│   ├── Usuario
│   ├── Perfil
│   ├── Curso
│   ├── Topico
│   └── Respuesta
│
├── repository
│
├── security
│   ├── JwtService
│   ├── JwtFilter
│   └── UsuarioDetailsService
│
└── ForoHubApplication
```

---

##  Autenticación

La API utiliza **JWT (JSON Web Token)** para proteger los endpoints.

### Flujo:

1. Registrar usuario → `POST /auth/register`
2. Login → `POST /auth/login`
3. Recibir token
4. Enviar token en cada request protegida:

```
Authorization: Bearer TU_TOKEN
```

---

##  Endpoints principales

###  Autenticación

| Método | Endpoint       | Descripción       |
| ------ | -------------- | ----------------- |
| POST   | /auth/register | Registrar usuario |
| POST   | /auth/login    | Iniciar sesión    |

---

###  Tópicos

| Método | Endpoint      | Descripción       |
| ------ | ------------- | ----------------- |
| GET    | /topicos      | Listar tópicos    |
| POST   | /topicos      | Crear tópico      |
| PUT    | /topicos/{id} | Actualizar tópico |
| DELETE | /topicos/{id} | Eliminar tópico   |

---

###  Respuestas

| Método | Endpoint         | Descripción          |
| ------ | ---------------- | -------------------- |
| POST   | /respuestas      | Crear respuesta      |
| PUT    | /respuestas/{id} | Actualizar respuesta |
| DELETE | /respuestas/{id} | Eliminar respuesta   |

---

##  Documentación Swagger

Una vez iniciada la aplicación:

```
http://localhost:8080/swagger-ui.html
```

Puedes usar el botón **Authorize** para ingresar el JWT.

---

## Base de Datos

El proyecto utiliza MySQL.

### Crear base de datos:

```sql
CREATE DATABASE forohub;
```

Configurar en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Ejecutar el proyecto

1. Clonar repositorio:

```
git clone https://github.com/tuusuario/forohub.git
```

2. Entrar al proyecto:

```
cd forohub
```

3. Ejecutar:

```
mvn spring-boot:run
```

---

##  Ejemplo de uso (Login)

```bash
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{
  "correoElectronico": "usuario@email.com",
  "contrasena": "123456"
}'
```

Respuesta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

##  Seguridad

* Contraseñas encriptadas con BCrypt
* Autenticación stateless
* Control de acceso por roles (ROLE_USER / ROLE_ADMIN)
* Protección contra CSRF deshabilitada para API REST

---




