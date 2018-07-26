FROM openjdk:10-jdk
EXPOSE 8080
COPY ./build/libs/recognition-service-docker-1.0.0.jar /home
CMD ["java","--add-opens","java.base/java.lang=ALL-UNNAMED","-jar", "/home/recognition-service-docker-1.0.0.jar"]