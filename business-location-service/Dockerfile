FROM bellsoft/liberica-openjdk-alpine-musl:21
LABEL org.opencontainers.image.authors='egualpam'
COPY business-location-service/target/business-location-service-0.0.1-SNAPSHOT.jar business-location-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "business-location-service-0.0.1-SNAPSHOT.jar"]