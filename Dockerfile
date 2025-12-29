FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ARG JAR_FILE=target/springboot-redis-*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]

