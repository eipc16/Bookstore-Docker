# Exercise 3 - PIISW
## Starting application
To start application run command
```
docker-compose up
```
in main directory. It should start 4 containers:
- `proxy_service` - main container responsible for redirecting requests
- `users_service_container` - container with mocked server that handles GET request
- `library_service_container` - container with mocked server that handles POST request
- `book_service_container` - container with mocked server that handles PUT request

To start only containers with wiremock servers use:
```
docker-compose -f ./docker-compose-wiremock.yml up
```
Services will be available at:
- `users_service_container`: `http://localhost:8010`
- `library_service_container`: `http://localhost:8011`
- `book_service_container`: `http://localhost:8012`

## Using proxy
Once all containers are started you can use `proxy-service` by performing one of the following request:

### GET `http://localhost:8080/library?id=<USER_ID>`
Response:
```json

```
### POST `http://localhost:8080/library`
* Request Body:
```json
{
  "json": "mock"
}
```
* Response:
```json
{
  "json": "mock"
}
```
### PUT `http://localhost:8080/library`
* Request Body: 
```json
{
  "json": "mock"
}
```
* Response:
```json
{
  "json": "mock"
}
```

## Running tests
To start tests simply type:
```
./mvnw test
```
in main directory or start them directly