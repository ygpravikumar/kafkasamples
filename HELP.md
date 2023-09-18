# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.10/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.10/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.10/reference/htmlsingle/index.html#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.0.10/reference/htmlsingle/index.html#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.10/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.0.10/reference/htmlsingle/index.html#actuator)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Kafka commands
https://kafka.apache.org/quickstart
```
Download kafka zip from above link and run below commands once extracted from inside the extracted folder
bin/zookeeper-server-start.sh config/zookeeper.properties
Start the Kafka broker service
bin/kafka-server-start.sh config/server.properties
bin/kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092
bin/kafka-topics.sh --delete --topic basic-json-topic --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic string-topic --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic basic-json-topic --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic payload-with-header-topic --bootstrap-server localhost:9092
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic string-topic 
bin/kafka-get-offsets.sh --bootstrap-server localhost:9092
bin/kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic basic-json-topic 
{"firstName":"Ravi", "lastName":"G" , "randomProperty": "1"}
```

### SQl Commands
```
To bring sql server from sqldocker folder:  docker-compose up -d 
SQL Server details:
    ui port: 8080
    url: "jdbc:mysql://127.0.0.1:3306/persondb"
    username: root
    password: example
```

#### process to configure jpa in springboot
1. Add gradle dependency
```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.18'
```
2. Add sql connection details in app yml
```
spring:
  jpa:
    generate-ddl: true
    show-sql: true
    database-platform: "org.hibernate.dialect.MySQL5Dialect"
    hibernate:
      dialect: "org.hibernate.dialect.MySQL5Dialect"
      ddl-auto: update
  datasource:
    driver-class-name: "com.mysql.cj.jdbc.Driver"
    url: "jdbc:mysql://127.0.0.1:3306/persondb"
    username: root
    password: example
    hikari:
      <hikari details>
```
3. Enable jpa repository
4. Enable entity scan
```
@EnableJpaRepositories("com.example")
@EntityScan("com.example")
```
5. Create entity java object
6. Create repository java object
7. Use repository object where needed

### Local development setup
1. Bring up kafka 
2. Bring up sql server
3. Boot run application


