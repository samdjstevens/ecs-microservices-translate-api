FROM azul/zulu-openjdk-alpine:11-jre
COPY build/libs/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app.jar"]