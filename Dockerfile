FROM openjdk:17.0.2
VOLUME /tmp
EXPOSE 8080
ADD target/acdc-0.0.1-SNAPSHOT.jar acdc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/acdc-0.0.1-SNAPSHOT.jar"]
