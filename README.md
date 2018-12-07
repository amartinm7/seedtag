![springboot](https://www.seedtag.com/assets/common/icons/logo-inverse.svg)

# Seedtag
Bootstrap application for testing a rest API

## run springboot app
```
gradle wrapper
./gradlew build
./gradlew build bootRun
```

or run fat jar
```
java -jar build/libs/radar-rest-service-0.1.0.jar
```

## Dockerize the app: create a docker image and run the project from command line
From the project root folder exec the commands to create a docker image and run it:

```bash
docker build -f docker/Dockerfile . -t radar
docker run -p 8888:8888 radar
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

## get health
curl -X GET -i "http://localhost:8888" -H "Content-type: application/json" -H "Accept: application/json"

## post greetings
curl -X POST -i "http://localhost:8888/radar" -H "Content-type: application/json" -H "Accept: application/json" -d '{"protocols":["avoid-mech"],"scans":[{"coordinates":{"x":0,"y":40},"enemies":{"type":"soldier","number":10}}]}'

## References
https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#spring-webflux
http://cloud.spring.io/spring-cloud-static/Finchley.SR1/single/spring-cloud.html
https://docs.spring.io/spring-security/site/docs/4.0.x/reference/htmlsingle/#csrf-configure

## create a new spring project from the scratch
First of all, you can generate the scaffolding of this project using the INITIALIZR tool: 
https://start.spring.io/

## actuator spring plugin for health check, status and stop service
```
curl localhost:8888/actuator/health
curl localhost:8888/actuator/info
curl localhost:8888/actuator/shutdown
```

# TIPS

## killing processes

```
ps -aux | grep "radar-rest-service-0.1.0.jar"
kill -9 $(jps | grep -i "radar-rest-service-0.1.0.jar" | awk '{print $1}')
```

## closing ports
```
sudo lsof -i :8888
sudo kill -9 PID
```

## installing spring cli
https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#getting-started-installing-the-cli
```
sdk install springboot
```

## spring guides
https://spring.io/guides/gs/gradle/
https://spring.io/guides/gs/actuator-service/


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
