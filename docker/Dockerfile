FROM openjdk:8-jdk-alpine

MAINTAINER Antonio Martin

EXPOSE 8888

COPY build/libs/lib/*.jar build/libs/*.jar /usr/local/radar/

WORKDIR /usr/local/radar

CMD java -Xmx512m -cp "*" org.springframework.boot.loader.JarLauncher