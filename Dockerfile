FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD target/acdc-0.0.1-SNAPSHOT.jar acdc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/acdc-0.0.1-SNAPSHOT.jar"]
