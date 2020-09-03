## Overview

Test bed for WebSockets application


## Getting Started

### Running tests

```
mvn clean test
```


### Test Coverage

```
mvn clean verify
```


### Running the Application

```
mvn clean spring-boot:run
```

The application listens on port 8080 by default


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
* [Spring properties](https://www.baeldung.com/properties-with-spring)
* [Spring value annotation](https://www.baeldung.com/spring-value-annotation)
* [Spring TestPropertySource annotation](https://www.baeldung.com/spring-test-property-source)
* [Sprint Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)
* []

### WebSockets

* [Browser WebSocket example](https://javascript.info/websocket)
* [Spring WebSockets and STOMP](https://spring.io/guides/gs/messaging-stomp-websocket/)


### Java Management Extensions

* [Spring MBean annotations](https://www.logicbig.com/tutorials/spring-framework/spring-integration/annotation-based-spring-jmx-integration.html)
* Dropwizard (an alternative to Spring Boot) has a metrics library
 * This includes a Histogram JMX bean 
 * [Dropwizard Histogram Metrics](https://github.com/dropwizard/metrics/blob/release/4.1.x/metrics-jmx/src/main/java/com/codahale/metrics/jmx/JmxReporter.java)
 * This code uses the underlying [Histogram class](https://github.com/dropwizard/metrics/blob/39fe8e8e1ce82516ad6ec6cdbf18a71f23eff6bb/metrics-core/src/main/java/com/codahale/metrics/Histogram.java)
 * That can store data in a [Uniform Reservoir](https://github.com/dropwizard/metrics/blob/39fe8e8e1ce82516ad6ec6cdbf18a71f23eff6bb/metrics-core/src/main/java/com/codahale/metrics/UniformReservoir.java)
 * The calculations for min/max/percentiles are carried out in the app and surfaced with JMX as final values as well as an array of data
* The Dropwizard metrics are used by [Cassandra](https://murukeshm.github.io/cassandra/3.10/operating/metrics.html)
* [Spring JMX](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/jmx.html#jmx-interface-metadata)

 
### HTML

* [HTML Doctype](https://www.w3schools.com/tags/tag_doctype.asp)
* [JavaScript location](https://www.tutorialrepublic.com/javascript-tutorial/javascript-window-location.php)
* [JavaScript load order](http://xahlee.info/js/js_executing_order.html)
* [WebSocket JavaScript API](https://developer.mozilla.org/en-US/docs/Web/API/WebSocket)


### Java

* [Project lombok maven setup](https://projectlombok.org/setup/maven)
* [Project lombok logging](https://projectlombok.org/features/log)
* [Creating logger without using class name](https://stackoverflow.com/questions/5271016/java-self-static-reference)
* [Unit tests with Mockito](https://www.vogella.com/tutorials/Mockito/article.html)
* [Configuring logback](https://dzone.com/articles/configuring-logback-with-spring-boot)
* [Generics wildcards](https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html)
* [Coverage with JaCoCo](https://mkyong.com/maven/maven-jacoco-code-coverage-example/)
* [Mockito annotations](https://www.baeldung.com/mockito-annotations)
