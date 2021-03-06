[![BCH compliance](https://bettercodehub.com/edge/badge/amartinm7/seedtag?branch=master)](https://bettercodehub.com/)

![springboot](https://www.seedtag.com/assets/common/icons/logo-inverse.svg)

# Seedtag Codetest 2: Backend Engineer
Bootstrap application for testing a rest API, Codetest 2

## ![springboot](./_media/icons/springboot.png) run springboot app
```bash
gradle wrapper
./gradlew build
./gradlew build bootRun
```

or run fat jar
```bash
java -jar build/libs/radar-rest-service-0.1.0.jar
```

## ![docker](./_media/icons/docker.png) Dockerize the app
From the project root folder exec the commands to create a docker image and run it:

for macosx start the docker daemon
```bash
killall Docker && open /Applications/Docker.app
```

then execute the next commands in order to create the docker image and run it:
```bash
docker build -f docker/Dockerfile . -t radar
docker run -p 8888:8888 radar
```

to stop the application first we have to stop the docker process and then kill the docker process:
```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

to clean the docker images from the system:
```bash
docker images
docker rmi PID
``` 

Once you have the dockerized app is really easy bring it to the cloud. You look for a cloud provider to deploy it and host it.

## ![swagger](./_media/icons/swagger.png) Swagger
You can see the swagger documentation in the following url:
- http://localhost:8888/swagger-ui.html

## get health
curl -X GET -i "http://localhost:8888" -H "Content-type: application/json" -H "Accept: application/json"

## post radar
curl -X POST -i "http://localhost:8888/radar" -H "Content-type: application/json" -H "Accept: application/json" -d '{"protocols":["avoid-mech"],"scans":[{"coordinates":{"x":0,"y":40},"enemies":{"type":"soldier","number":10}}]}'

## actuator spring plugin for health check, status and stop service
```bash
curl localhost:8888/actuator/health
curl localhost:8888/actuator/info
curl localhost:8888/actuator/shutdown
```

# TIPS

## killing processes

```bash
ps -aux | grep "radar-rest-service-0.1.0.jar"
kill -9 $(jps | grep -i "radar-rest-service-0.1.0.jar" | awk '{print $1}')
```

## closing ports
```bash
sudo lsof -i :8888
sudo kill -9 PID
```

## installing spring cli
https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#getting-started-installing-the-cli
```bash
sdk install springboot
```

## intellij tips

### avoid out folder
File | Project Structure | Project Settings | Modules | Paths tab | Compiler output
Select 'use module compile output path' and add the build main and test folders

### add navigation arrows to the toolbox
preferences | navigation bar toolbar
click + button add the left and right arrows from the navigation list

### organize imports like eclipse
preferences | editor | general | auto imports
marks the checks with 'unambiguos' labels 

# spring guides

- http://spring.io/guides
- https://spring.io/guides/gs/gradle/
- https://spring.io/guides/gs/actuator-service/

## create a new spring project from the scratch
First of all, you can generate the scaffolding of this project using the INITIALIZR tool: 
https://start.spring.io/
