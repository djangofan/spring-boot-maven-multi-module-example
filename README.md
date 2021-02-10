# spring-boot-maven-multi-module-example

Simplest JDBC JPA example with Crud operations

## IMPORTANT!!

THIS example demonstrates a multi-module project where Spring-Boot loads `application.properties` from `{parent}/config` folder.


## Execution 

This works:

    mvn spring-boot:run -pl spring-boot-module

But this will also work:

    mvn exed:java -pl spring-boot-module

You will notice this arg is configured to be passed in pom file:

    -Dspring.profiles.active=dev
