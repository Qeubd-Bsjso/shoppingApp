# shoppingApp

## About
This is a sample project usring spring boot. It builds a backend for a basic shopping cart for one product. 

It was made using Spring Boot, Spring Data JPA and Spring Data REST. This is a sample project so in memory H2 Database is used. 

Here user can place order for different products and and simulate the payment for the orders.

## How to run

### Using Maven Wrapper
This project can run from command line using included Maven Wrapper.

Go to the root folder of application and type:
```bash
$ chmod +x mvnw
$ ./mvnw spring-boot:run
```


### Using Executable jar

 JAR file can be build using
```bash
 $ ./mvnw clean package
```
 Then run the JAR file
```bash
 $ java -jar target/shoppingApp-0.0.1-SNAPSHOT.jar
```

### Eclipse IDE
 This project was build using eclipse IDE. Load the project from files and then run ShoppingAppApplication.java file using run button.

## H2 Database Web interface

After running application, go to your web browser and visit `http://localhost:8080/h2-console/`

In field **JDBC URL** put
```
jdbc:h2:mem:mydata
```
Here you can see current state of database in real time.  

