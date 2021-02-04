FROM openjdk:11

MAINTAINER masmovil

COPY target/mm-code-0.0.1-SNAPSHOT.jar /app/mm-code-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java"]
CMD ["-jar", "/app/mm-code-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080