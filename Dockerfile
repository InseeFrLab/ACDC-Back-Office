FROM openjdk:17
WORKDIR /app
EXPOSE 8080
ADD target/acdc-0.0.1-SNAPSHOT.jar acdc-0.0.1-SNAPSHOT.jar

COPY src/main/resources/static/emptyPdf.xsl /app/src/main/resources/static/emptyPdf.xsl
COPY src/main/resources/static/emptyPdf.xml /app/src/main/resources/static/emptyPdf.xml

ENTRYPOINT ["java","-jar","/acdc-0.0.1-SNAPSHOT.jar"]
