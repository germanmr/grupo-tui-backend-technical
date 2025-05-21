## Summary

TUI DX Backend technical Test v2

The base project uses lombok, so you have to install it. You can use the following
guide https://www.baeldung.com/lombok-ide

### Install and generate Jacoco Report

    mvn clean install

generates the jacoco report that can be found in the Jacoco Aggregate Module

Location:

Windows

    \jacoco-aggregate\target\site\jacoco-aggregate\index.html

Linux/Mac

    /jacoco-aggregate/target/site/jacoco-aggregate/index.html

Run OrderApplication class

A valid request using Curl or access the [Swagger doc URL](HTTP://LOCALHOST:8181/swagger)

    curl --location 'http://localhost:8181/order' \
    --header 'Content-Type: application/json' \
    --data '{
        "clientId": 1,
        "productId": 1,
        "quantity": 1
    }'

* Only one client code: 1
* Only one Product Code: 1 - "PILOTES"
* Quantity attribute must be bigger than one(1)

Expected reply

    {
        "orderNumber": "63a43c8a-9982-4b83-9dd1-7a768852c406",
        "client": {
            "clientId": 1,
            "firstName": "Esteve",
            "lastName": "Clerch",
            "telephone": "+34 972 123456",
            "deliveryAddress": {
            "street": "Carrer Sant Eduar 1",
            "postalCode": "17538",
            "city": "Das",
            "country": "España"
            }
        },
            "product": {
                "productId": 1,
                "name": "PILOTES",
                "price": 11.25
            },
            "quantity": 1,
            "orderTotal": 11.25
    }


An invalid request, in this case withouth the "clientId" attribute

    curl --location 'http://localhost:8181/order' \
    --header 'Content-Type: application/json' \
    --data '{
        "productId": 1,
        "quantity": 1
    }'

Expected reply

    {
        "code": "Bad Request",
        "message": "clientId no debe ser nulo"
    }


Technologies:
* Hexagonal
* Spring Boot
* Java 17
* OpenAPI
* Lombok
* Jacoco
* MapStruct
* H2 Database
    