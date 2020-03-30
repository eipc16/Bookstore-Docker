# Exercise 3 - PIISW

Build status: 
[![CircleCI](https://circleci.com/gh/pietrzakp/PIISW/tree/Lista3.svg?style=svg&circle-token=592904e950e365510a6de46c34bf268b64d1086f)](http://app.circleci.com/pipelines/github/pietrzakp/PIISW?branch=Lista3)

## Starting application
To start application run command

    docker-compose up

in main directory. It should start 4 containers:
- `proxy_service` - main container responsible for redirecting requests
- `users_service_container` - container with mocked server that handles GET request
- `library_service_container` - container with mocked server that handles POST request
- `book_service_container` - container with mocked server that handles PUT request

To start only containers with wiremock servers use:

    docker-compose -f ./docker-compose-wiremock.yml up

Services will be available at:
- `users_service_container`: `http://localhost:8010`
- `library_service_container`: `http://localhost:8011`
- `book_service_container`: `http://localhost:8012`

## Using proxy
Once all containers are started you can use `proxy-service` by performing one of the following request:

### GET `http://localhost:8080/library?id=<USER_ID>`
* Response example:
```json
{
    "id": <USER_ID>,
    "firstName": "Przemysław",
    "lastName": "Pietrzak",
    "address": {
        "country": "Polska",
        "city": "Wrocław",
        "street": "Ulicowa",
        "streetNumber": 5
    }
}
```
* Error example:
```json
{
    "exception": "ExternalServiceException",
    "timestamp": "2020-03-30T17:18:27.916216",
    "status": 412,
    "error": "Precondition Failed",
    "message": "User with id: <USER_ID> does not exist",
    "service": "users-service",
    "localPath": "/library"
}
```
### PUT `http://localhost:8080/library`
* Request Body: 
```json
{
    "id": 5,
    "bookName": "New book",
    "authorName": "author"
}
```
* Response:
```json
{
    "serviceName": "book-service (PUT)",
    "result": "SUCCESS",
    "message": "Successfully put book with id: 5!"
}
```
* Error example:
```json
{
    "exception": "ExternalServiceException",
    "timestamp": "2020-03-30T17:21:59.930308",
    "status": 412,
    "error": "Precondition Failed",
    "message": "Book id cannot be less than zero!",
    "service": "book-service",
    "localPath": "/library"
}
```

### POST `http://localhost:8080/library`
* Request Body:
```json
{
    "userId": 6,
    "bookId": 6
}
```
* Response example:
```json
{
    "serviceName": "library-service (POST)",
    "result": "SUCCESS",
    "message": "Successfully added new book rental!"
}
```
* Error example 1:
```json
{
    "exception": "ExternalServiceException",
    "timestamp": "2020-03-30T17:20:21.208138",
    "status": 412,
    "error": "Precondition Failed",
    "message": "User with id: 1 already rented a book with id 1",
    "service": "library-service",
    "localPath": "/library"
}
```
* Error example 2:
```json
{
    "exception": "ExternalServiceException",
    "timestamp": "2020-03-30T17:20:36.027495",
    "status": 412,
    "error": "Precondition Failed",
    "message": "There is no book with id: 5",
    "service": "library-service",
    "localPath": "/library"
}
```
## Running tests
Instructions on running tests

### JUnit (with WireMock)
To start tests simply type:

    ./mvnw test

in main directory or start them directly

### Integration tests (with Postman)
Collection with integration tests can be found under:

    ./postman-integration-tests/PIISW.postman_collection.json
    
Starting `Collection Run` on given collection will start prepared tests