FROM openjdk:18-alpine
EXPOSE 8080
ADD target/patient-module-system.jar patient-module-system.jar
ENTRYPOINT ["java","-jar","/patient-module-system.jar"]