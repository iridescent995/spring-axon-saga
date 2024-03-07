# spring-axon-saga
This Example shows how to configure saga framework in a spring boot application using AXON framework

## Pre Requisite 
Download and run AXON server from here.
Run
`
java -jar axonserver.jar
`
you can access Axon server dashboard in this link. http://localhost:8024/

## Running the Application 

Execute 
`
mvn clean compile exec:java
`
you can hit this POST url from postman. 

curl --location 'localhost:8080/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "name": "accountName"
}'



