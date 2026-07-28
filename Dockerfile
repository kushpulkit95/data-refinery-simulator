FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/data-refinery-simulator-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java"]
CMD ["-jar","app.jar"]