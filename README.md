# Product Microservice 🎯

Es un microservicio diseñado para gestionar productos, marcas, categorias y sus imágenes en una plataforma de venta. Permite administrar productos, categorías, marcas e imágenes asociadas, integrando almacenamiento en AWS S3 y una base de datos PostgreSQL.

---

## 🚀 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL 15**
- **AWS S3 SDK** (almacenamiento de imágenes)
- **Docker & Docker Compose**
- **Swagger/OpenAPI 3** (documentación de la API)
- **Lombok** (para reducir boilerplate)
- **MapStruct** (mapeo entre entidades y DTOs)
- **Arquitectura Hexagonal + DDD** (Domain-Driven Design)

---

## 🏗️ Arquitectura y Patrones

- **Arquitectura Hexagonal (Ports & Adapters)**  
  Aísla el core de la aplicación de las tecnologías externas (BD, AWS, etc.).

- **Domain-Driven Design (DDD)**  
  Separa el dominio, la lógica de aplicación y los adaptadores.

- **Transactional Facade Pattern**  
  Maneja las transacciones a nivel de infraestructura para mantener el core limpio de dependencias de Spring.

- **Delegates**  
  Utiliza delegates para romper ciclos de dependencia entre servicios, como en el caso de eliminar imágenes asociadas a productos.

---

## ⚙️ Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto basándote en el siguiente ejemplo:
(O copia y pega el contenido del archivo `.env.sample`)

```env
DB_HOST=db
DB_PORT=5432
DB_NAME=products_db
DB_USERNAME=user
DB_PASSWORD=pass

AWS_BUCKET_NAME=product-bucket
AWS_REGION=us-east-1
AWS_ACCESS_KEY=your_aws_access_key
AWS_SECRET_KEY=your_aws_secret_key
```

---

## 🐳 Instrucciones para correr con Docker

1. Clona el proyecto:

   ```bash
   git clone https://github.com/MrxSteve/Hexagonal-ProductsAPI.git
   cd product-mservice
   ```

2. Crea tu archivo `.env` (basado en `.env.sample`).

3. Construye el JAR:

   ```bash
   mvn clean package -DskipTests
   ```

4. Levanta los contenedores:

   ```bash
   docker-compose up --build
   ```

5. Accede a Swagger: [http://localhost:8085/swagger-ui/index.html](http://localhost:8085/swagger-ui/index.html)

---

## 📚 Principales Endpoints

### Productos `/api/productos`
- **POST /** → Crear producto
- **GET /** → Listar productos (paginado)
- **GET /{id}** → Buscar producto por ID (incluye imágenes)
- **PUT /{id}** → Actualizar producto
- **DELETE /{id}** → Eliminar producto (elimina también las imágenes de AWS S3)
- **GET /filtrar** → Filtrar productos (nombre, marca, categoría, estado)

### Categorías `/api/categorias`
- **POST /** → Crear categoría
- **GET /** → Listar categorías
- **GET /{id}** → Buscar categoría por ID
- **PUT /{id}** → Actualizar categoría
- **DELETE /{id}** → Eliminar categoría
- **GET /nombre?nombre=xyz** → Buscar categorías por nombre

### Marcas `/api/marcas`
- **POST /** → Crear marca
- **GET /** → Listar marcas
- **GET /{id}** → Buscar marca por ID
- **PUT /{id}** → Actualizar marca
- **DELETE /{id}** → Eliminar marca
- **GET /nombre?nombre=xyz** → Buscar marcas por nombre

### Imágenes `/api/imagenes`
- **POST /{productoId}** → Subir imagen a producto (Multipart)
- **GET /producto/{productoId}** → Listar imágenes de un producto
- **GET /{imagenId}** → Buscar imagen por ID
- **DELETE /{imagenId}** → Eliminar imagen por ID
- **DELETE /producto/{productoId}/imagen/{imagenId}** → Eliminar imagen específica de un producto
- **DELETE /producto/{productoId}** → Eliminar todas las imágenes de un producto

---

## 📝 Licencia

Distribuido bajo licencia MIT.  
Desarrollado con 💻 por Steve [@MrxSteve](https://github.com/MrxSteve).
