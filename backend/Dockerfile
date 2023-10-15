FROM openjdk:19-alpine
COPY target/*.jar truegain-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "truegain-api.jar"]
