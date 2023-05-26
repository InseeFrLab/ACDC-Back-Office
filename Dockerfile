FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD target/acdc-0.0.1-SNAPSHOT.jar acdc-0.0.1-SNAPSHOT.jar
COPY src/main/resources/static/emptyPdf.xsl /tmp/src/main/resources/static/emptyPdf.xsl
COPY src/main/resources/static/emptyPdf.xml /tmp/src/main/resources/static/emptyPdf.xml
ENTRYPOINT ["java","-jar","/acdc-0.0.1-SNAPSHOT.jar"]
