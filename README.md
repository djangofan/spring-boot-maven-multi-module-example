# spring-boot-maven-multi-module-example

Simplest JDBC JPA example with Crud operations

## IMPORTANT!!

THIS example demonstrates a multi-module project where Spring-Boot loads `application.properties` from `{parent}/config` folder.


## Execution 

This doesn't work, and I am not sure why:

    mvn spring-boot:run -pl spring-boot-module -Dspring.profiles.active=dev

But this will work:

    mvn exed:java -pl spring-boot-module -Dspring.profiles.active=dev
