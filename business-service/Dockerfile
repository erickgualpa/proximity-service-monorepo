FROM bellsoft/liberica-openjdk-alpine-musl:21
LABEL org.opencontainers.image.authors='egualpam'
COPY business-service/target/business-service-0.0.1-SNAPSHOT.jar business-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "business-service-0.0.1-SNAPSHOT.jar"]