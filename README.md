Movie-Demo 0.0.1-SNAPSHOT
----------------------------------------------------
For building and running the application you need:

JDK 1.8
Maven
Lombok plugin

----------------------------------------------------
Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method
in the com.example.moviedemo.MovieDemoApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

* mvn spring-boot:run

Note:
If you want to run this code on MySQL, please comment the h2 configuration and un-comment the mysql configuration from the
application.yml file.

----------------------------------------------------
This application using in memory database h2. You can access the h2 database console on your local machine using below URL:

* http://localhost:8080/h2
