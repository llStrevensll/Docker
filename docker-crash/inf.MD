#Info about Docker for this projects

```
docker build -t llstrevensll/hello-world-rest-api:dockerfile1 .
docker run -d -p 8080:8080 llstrevensll/hello-world-rest-api:dockerfile1
docker logs 23d859d310ab31d053
```

```
mvn package -DskipTests
```

```
<repository>llstrevensll/${project.name}</repository> 
//project.name = <name>hello-world-rest-api</name>

<tag>${project.version}</tag> 
//project.version = <version>0.0.1-SNAPSHOT</version>
```

```
docker images
docker run -d -p 8080:8080 llstrevensll/hello-world-rest-api:0.0.1-SNAPSHOT
```

##Caching docker image to add dependencies in another stage

https://spring.io/guides/topicals/spring-boot-docker/
https://stackoverflow.com/questions/18093928/what-does-could-not-find-or-load-main-class-mean

Use Plugin maven-dependency-plugin
```
<id>unpack</id>
		<phase>package</phase>
			<goals>
				<goal>unpack</goal>
			</goals>
```

```
mvn clean package
docker build -t llstrevensll/hello-world-rest-api:dockerfile1 .
docker run -d -p 8080:8080 llstrevensll/hello-world-rest-api:dockerfile1
docker exec -it 33e56ff4deec /bin/sh
```

=======
* CMD - Default parameters

When you pass parameters from command line, Your default parameters are overridden.

```
docker run -p 8080:8080 llstrevensll/todo-web-application-h2:0.0.1-SNAPSHOT ping google.com
```

* ENTRYPOINT

Is a way of making your container as an executable. When you pass
any arguments to the container, those arguments will not override the ENTRYPOINT

```
docker run -p 8080:8080 llstrevensll/hello-world-rest-api:dockerfile1 param1 param2
```

But if you use the argument --entrypoint, then you can override the ENTRYPOINT which is configured

---
push image with spotify dockerfile plugin
You need configure your file settings.xml

```
<servers>
  <server>
    <id>docker-repo.example.com:8080</id>
	<username>me</username>
	<password>mypassword</password>
  </server>
</servers>
```

========
MYSQL
With h2, in this case the scope is "test"
```
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>
```

---
*create: (default for H2) creates the schema, destroying previous data
*update: update the schema
spring.jpa.hibernate.ddl-auto=update


--
image in dockerhub: https://hub.docker.com/_/mysql/?tab=description&page=1&ordering=last_updated
```
docker run mysql:5.7
```

```
 [ERROR] [Entrypoint]: Database is uninitialized and password option is not specified
    You need to specify one of the following:
    - MYSQL_ROOT_PASSWORD
    - MYSQL_ALLOW_EMPTY_PASSWORD
    - MYSQL_RANDOM_ROOT_PASSWORD

docker run -d -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=todos -e MYSQL_USER=todos-user -e MYSQL_PASSWORD=dummytodos -p 3306:3306 mysql:5.7
```

complete command:
```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_DATABASE=todos --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos  --name mysql --publish 3306:3306 mysql:5.7
```

MySql Shell: https://dev.mysql.com/downloads/shell/

```
PS C:\Users\Strevens> mysqlsh
```
//I would need to pass in which database to connect to and which user id to use

connect

```
MySQL  JS > \connect todos-user@localhost:3306

Creating a session to 'todos-user@localhost:3306'
Please provide the password for 'todos-user@localhost:3306': **********
Save password for 'todos-user@localhost:3306'? [Y]es/[N]o/Ne[v]er (default No): y
Fetching schema names for autocompletion... Press ^C to stop.
Your MySQL connection id is 3
Server version: 5.7.35 MySQL Community Server (GPL)
No default schema selected; type \use <schema> to set one
```
```
 MySQL  localhost:3306 ssl  JS > \sql
Switching to SQL mode... Commands end with ;

 MySQL  localhost:3306 ssl  SQL > show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| todos              |
+--------------------+
2 rows in set (0.0030 sec)
 MySQL  localhost:3306 ssl  SQL > show schemas;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| todos              |
+--------------------+
2 rows in set (0.0033 sec)
 MySQL  localhost:3306 ssl  SQL >

MySQL  localhost:3306 ssl  SQL > use todos;
Default schema set to `todos`.
Fetching table and column names from `todos` for auto-completion... Press ^C to stop.
 MySQL  localhost:3306 ssl  todos  SQL >


 MySQL  localhost:3306 ssl  todos  SQL > select * from todo;
Empty set (0.0050 sec)
```
--
```
mvn clean package
```
  in the pom.xml you can generate the image

```
docker run -p 8080:8080 llstrevensll/todo-web-application-mysql:0.0.1-SNAPSHOT
```

**//So, the default networking mode in Docker is something called Bridge**
## Bridge
```
Error:
[ost-startStop-1] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Exception during pool initialization.

com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure

The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
 WARN 1 --- [ost-startStop-1] o.s.b.a.orm.jpa.DatabaseLookup   : Unable to determine jdbc url from datasource
```

One option is use --link in docker
--link=<names>  -> names is colum por the name of your container

Also use environment variables in file . properties
${}
RDS_HOSTNAME:mysql   -> container name for example mysql, or random name as "musing_cartwright"
RDS_PORT
RDS_DB_NAME
RDS_USERNAME
RDS_PASSWORD

```
docker run -p 8080:8080 --link=musing_cartwright -e RDS_HOSTNAME=musing_cartwright llstrevensll/todo-web-application-mysql:0.0.1-SNAPSHOT
```

```
PS C:\Users\Strevens> docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
9a2ea890d710   bridge    bridge    local
c11cjiasdfba   host      host      local
7e36a0832fee   none      null      local

PS C:\Users\Strevens> docker inspect bridge
//Identify the cointainers in this network
```


###Host
Other option is with host
We would want to directly expose everithing on the host.
```
docker run -p 8080:8080 --network=host llstrevensll/todo-web-application-mysql:0.0.1-SNAPSHOT
```

###None
This container will not have any network at all
```
docker run -p 8080:8080 --network=none llstrevensll/todo-web-application-mysql:0.0.1-SNAPSHOT
```

###Create custom network

```
docker network create web-application-mysql-network 
docker network ls
```

lauch container mysql y container for the web app
```
docker run -d -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=todos -e MYSQL_USER=todos-user -e MYSQL_PASSWORD=dummytodos  --name mysql -p 3306:3306 --network=web-application-mysql-network mysql:5.7
```

```
docker run -d -p 8080:8080 --network=web-application-mysql-network -e RDS_HOSTNAME=mysql llstrevensll/todo-web-application-mysql:0.0.1-SNAPSHOT
```

###Volumen

This should contain the data which is present inside the MySQL database
/var/lib/mysql

```
docker run -d -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=todos -e MYSQL_USER=todos-user -e MYSQL_PASSWORD=dummytodos  --name mysql -p 3306:3306 --network=web-application-mysql-network  --volume mysql-database-volume:/var/lib/mysql  mysql:5.7
```
```
docker run -d -p 8080:8080 --network=web-application-mysql-network -e RDS_HOSTNAME=mysql llstrevensll/todo-web-application-mysql:0.0.1-SNAPSHOT
```



