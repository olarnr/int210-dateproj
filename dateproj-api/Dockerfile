FROM openjdk:17-jdk-alpine
WORKDIR code
COPY . .
RUN ./mvnw clean package
CMD ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]
