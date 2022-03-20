FROM openjdk:17
COPY ./target/cloth-database-0.0.1-SNAPSHOT.jar /usr/src/cloth/
WORKDIR /usr/src/cloth/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cloth-database-0.0.1-SNAPSHOT.jar"]