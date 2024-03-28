FROM eclipse-temurin:17-jdk as build

COPY target/inventario-0.0.1.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]