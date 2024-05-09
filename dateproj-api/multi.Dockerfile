FROM openjdk:17-jdk-alpine AS build
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw install -DskipTests
RUN mkdir -p target/extracted && java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

FROM openjdk:17-jdk-alpine AS run
VOLUME /tmp
ARG EXTRACTED=/workspace/app/target/extracted
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]

