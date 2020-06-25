## Overview

Test bed for WebSockets application


## Getting Started

### Running the Application

## Setup

### Creation from Archetype

Ran the following command to create this project

```
mvn archetype:generate \
  -DarchetypeGroupId=org.springframework.boot \
  -DarchetypeArtifactId=spring-boot-sample-simple-archetype \
  -DpackageName=org.test.websocket \
  -DgroupId=org.test.websocket \
  -DartifactId=websockets-test-application \
  -Dversion=1.0-SNAPSHOT -DoutputDirectory=..
```

There is a glitch in the archetype plugin where it always creates a directory for the artifactId, by setting the 
output to be one level up it effectively uses the current working directory if it's named the same.

Also the template project needs the Spring Boot version increasing to, in this case, `2.3.1.RELEASE`.  
On doing this the two tests provided will break, just delete them.


## References

### Maven

* [Maven directory layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
* [Logback](https://www.baeldung.com/logback)
* [Maven archetype quickstart](https://maven.apache.org/archetypes/maven-archetype-quickstart/)
* [Spring boot maven plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/html/)


### Spring Boot

* [Spring API documentation](https://docs.spring.io/spring-framework/docs/current/javadoc-api/s)
* [Spring boot example](https://spring.io/guides/gs/spring-boot/)
* [Serving static content](https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot)
* [Spring Task Executor](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/scheduling.html)
* [Testing spring web applications](https://spring.io/guides/gs/testing-web/)
* [Unit testing spring boot apps](https://reflectoring.io/unit-testing-spring-boot/)

### WebSockets

* [Browser WebSocket example](https://javascript.info/websocket)

### Java Management Extensions

* [Spring MBean annotations](https://www.logicbig.com/tutorials/spring-framework/spring-integration/annotation-based-spring-jmx-integration.html)


### HTML

* [HTML Doctype](https://www.w3schools.com/tags/tag_doctype.asp)
* [Spring JMX](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/jmx.html#jmx-interface-metadata)


### Java

* [Project lombok maven setup](https://projectlombok.org/setup/maven)
* [Project lombok logging](https://projectlombok.org/features/log)
* [Creating logger without using class name](https://stackoverflow.com/questions/5271016/java-self-static-reference)
* [Unit tests with Mockito](https://www.vogella.com/tutorials/Mockito/article.html)

