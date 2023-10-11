FROM --platform=linux/amd64 openjdk
VOLUME /tmp
EXPOSE 8080
COPY target/IlpTutorialRestService*.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]