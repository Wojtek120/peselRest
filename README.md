# Users details REST API
> Application gives access to two endpoints - listing of all users and adding a new one.

## Used
+ [Maven](https://maven.apache.org/)
+ [Spring boot](https://spring.io/projects/spring-boot)
+ [Hibernate](https://hibernate.org/)
+ [Spring Data](https://spring.io/projects/spring-data)
+ [Lombok](https://projectlombok.org/)
+ [ModelMapper](http://modelmapper.org/)
+ [JUnit](https://junit.org/junit5/)

## Setup

##### Clone application
```bash
git clone https://github.com/Wojtek120/peselRest
```

##### Create mySql database
```bash
CREATE DATABASE peselDatabase;
```

##### Run application
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--DBUSER=msql_username,--DBPASS=mysql_password
```

where instead *msql_username* and *msql_password* enter your username and password to MySql.

## Have fun

##### In order to add new user you can simply write for example
```bash
curl -X POST localhost:8080/users -H 'Content-type:application/json' -d '{"firstName": "Wojtek", "lastName": "Buczko", "pesel": "03093069448"}'
```

##### In order to list all users write command
```bash
curl -v localhost:8080/users
```

