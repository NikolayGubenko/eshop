FROM openjdk:11
ARG JAR_FILE=target/eshop-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eshop.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/eshop.jar"]