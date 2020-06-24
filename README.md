## Overview

Test bed for WebSockets application

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


## References


* [Maven directory layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
* [Logback](https://www.baeldung.com/logback)
* [Maven archetype quickstart](https://maven.apache.org/archetypes/maven-archetype-quickstart/)
* [Spring boot example](https://spring.io/guides/gs/spring-boot/)
* [Serving static content](https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot)
* [HTML Doctype](https://www.w3schools.com/tags/tag_doctype.asp)


