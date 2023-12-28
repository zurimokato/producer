# Utiliza una imagen base que tenga Java y Maven instalados
FROM maven:3.8.4-openjdk-17 AS builder

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto de la aplicación y realiza la construcción
COPY src src
RUN mvn package -DskipTests

# Etapa de construcción completa, cambiamos a una imagen más ligera
FROM amazoncorretto:17.0.7-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el artefacto construido desde la etapa anterior
COPY --from=builder /app/target/publisher-0.0.1-SNAPSHOT.jar publisher.jar

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 8083

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "publisher.jar"]