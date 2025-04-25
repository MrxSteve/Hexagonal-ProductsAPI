# Imagen base de Java
FROM eclipse-temurin:17-jdk

# Directorio dentro del contenedor
WORKDIR /app

# Copia el JAR generado por Maven
COPY target/product-mservice-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8085

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
