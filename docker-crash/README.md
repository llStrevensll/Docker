

## Diagrams

- Courtesy http://viz-js.com/

```
graph architecture {

node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28", fontcolor=white];
rankdir = TB
node[shape=record, width=3]

Containers, LocalImages [height=1]

DockerClient -- Daemon
Daemon -- Containers 
Daemon -- LocalImages
Daemon -- ImageRegistry

DockerClient[label=<Docker Client>]
ImageRegistry[label=<Image Registry <BR /><FONT POINT-SIZE="10">nginx<BR />mysql<BR />eureka<BR />your-app<BR /><BR /></FONT>>];
Daemon[label=<Docker Daemon>]


}


graph architecture {

node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28", fontcolor=white];
rankdir = TB
node[shape=record, width=2]
Hypervisor,HostOS, Hardware[shape=record, width=6.5, style=filled,color="#D14D28", fontcolor=white]
edge [width=0]
graph [pad=".75", ranksep="0.05", nodesep="0.25"];

Application1 -- Software1 [style=invis]
Application2 -- Software2 [style=invis]
Application3 -- Software3 [style=invis]

Software1 -- GuestOS1 [style=invis]
Software2 -- GuestOS2 [style=invis]
Software3 -- GuestOS3 [style=invis]
GuestOS1 -- Hypervisor [style=invis]
GuestOS2 -- Hypervisor [style=invis]
GuestOS3 -- Hypervisor [style=invis]
Hypervisor -- HostOS [style=invis]
HostOS -- Hardware [style=invis]

}


graph architecture {

node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28", fontcolor=white];
rankdir = TB
node[shape=record, width=2]
HostOS, CloudInfrastructure, DockerEngine[shape=record, width=6.5, style=filled,color="#D14D28", fontcolor=white]
edge [width=0]
graph [pad=".75", ranksep="0.05", nodesep="0.25"];
Container1,Container2,Container3[height=2]

Container1 -- DockerEngine [style=invis]
Container2 -- DockerEngine [style=invis]
Container3 -- DockerEngine [style=invis]
DockerEngine -- HostOS [style=invis]
HostOS -- CloudInfrastructure [style=invis]

}
```


### Commands Executed

```
docker container exec unruffled_tereshkova ls /tmp
docker container cp target/hello-world-rest-api.jar 54cf414254e48d5f68c4d468b2dd4cbdd95d17f9e2074fdb9df7f64987697f2b:/tmp
docker container commit unruffled_tereshkova in28min/hello-world-rest-api:manual 
docker run -p 8080:8080 in28min/hello-world-rest-api:manual
docker container commit --change='CMD ["java","-jar","/tmp/hello-world-rest-api.jar"]' unruffled_tereshkova in28min:hello-world-rest-api:manual2
docker run -p 8080:8080 in28min/hello-world-rest-api:manual2
docker inspect in28min/hello-world-rest-api:dockerfile1
docker history in28min/hello-world-rest-api:dockerfile1

docker build -t in28min/hello-world-rest-api:dockerfile1 .
docker run -p 8080:8080 in28min/hello-world-rest-api:dockerfile1
docker history in28min/hello-world-rest-api:dockerfile1

docker run -p 8080:8080 in28min/hello-world-rest-api:0.0.1-SNAPSHOT

mvn docker:build
docker run -p 8080:8080 webservices/01-hello-world-rest-api

docker run -dit 51297c224d60
docker container exec 7714 ls /maven
docker run -p 8080:8080 01-hello-world-rest-api:latest

#/02-todo-web-application-h2/
docker run -p 8080:8080 in28min/todo-web-application-h2:0.0.1-SNAPSHOT
docker run -p 8080:8080 in28min/todo-web-application-h2:0.0.1-SNAPSHOT ping google.com
docker run -p 8080:8080 in28min/hello-world-rest-api:dockerfile1 param1 param2
docker run -p 8080:8080 in28min/todo-web-application-mysql:0.0.1-SNAPSHOT

docker run -p 8080:8080 --network=host  in28min/todo-web-application-mysql:0.0.1-SNAPSHOT ping http://localhost:8080 

docker network ls
docker inspect bridge
docker container run -p 8080:8080 --link=mysql -e RDS_HOSTNAME=mysql  in28min/todo-web-application-mysql:0.0.1-SNAPSHOT

docker network create web-application-mysql-network
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 --network=web-application-mysql-network mysql:5.7
docker container run -p 8080:8080 --network=web-application-mysql-network -e RDS_HOSTNAME=mysql  in28min/todo-web-application-mysql:0.0.1-SNAPSHOT
docker container restart mysql
docker container run -p 8080:8080 --network=web-application-mysql-network -e RDS_HOSTNAME=mysql  in28min/todo-web-application-mysql:0.0.1-SNAPSHOT

docker container prune


docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 --network=web-application-mysql-network --volume mysql-database-volume:/var/lib/mysql  mysql:5.7


#/04-spring-boot-react-full-stack-h2/restful-web-services/
docker tag 3f4765872126 in28min/rest-api-full-stack:2stagebuild
docker run -p 8080:8080 in28min/rest-api-full-stack:2stagebuild

npm install
npm start
npm run build

docker network create currency-network
docker run -p 8000:8000 --network=currency-network --name=currency-exchange-service in28min/currency-exchange-service:0.0.1-SNAPSHOT
docker run -p 8100:8100 --network=currency-network --name=currency-conversion-service --env CURRENCY_EXCHANGE_URI=http://currency-exchange-service:8000 -d in28min/currency-conversion-service:0.0.1-SNAPSHOT

docker-compose up
docker-compose up -d
docker-compose scale currency-conversion-service=2
docker-compose logs
docker-compose logs -f

docker system prun
docker system prune -a

docker search mysql
docker images
docker tag in28min/todo-rest-api-h2:1.0.0.RELEASE latest
docker rmi latest:latest
docker pull mysql
docker image ls --format='{{json .}}'

docker container run -p 5000:5000 -d in28min/todo-rest-api-h2:1.0.0.RELEASE
docker container pause 6478
docker container unpause 6478

docker run -p 5000:5000 in28min/todo-rest-api-h2:0.0.1-SNAPSHOT
docker run -p 5000:5000 -d in28min/todo-rest-api-h2:0.0.1-SNAPSHOT
docker run -p 5000:5000 -d --restart=always in28min/todo-rest-api-h2:0.0.1-SNAPSHOT

docker events
docker top c710
docker stats
docker run -m 512m --cpu-quota 50000
docker system df

docker container stop 1b1
docker container kill 9b8

docker-compose config
docker-compose images
docker-compose ps
docker-compose top
docker-compose pause
docker-compose unpause
docker-compose rm
docker-compose build
docker-compose events

Deleted Networks:
web-application-mysql-network
03-todo-web-application-mysql_todo-web-application-network
currency-network
05-microservices_currency-compose-network
```
