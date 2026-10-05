FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 8083

CMD ["sh", "-c", "java -jar target/WorkHistory-0.0.1-SNAPSHOT.jar"]