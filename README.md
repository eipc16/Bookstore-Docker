# Exercise 3 - PIISW
## Starting application
To start application run command
```
docker-compose up
```
in main directory. It should start 4 containers:
- `proxy-service` - main container responsible for redirecting requests
- `wiremock_get_container` - container with mocked server that handles GET request
- `wiremock_post_container` - container with mocked server that handles POST request
- `wiremock_put_container` - container with mocked server that handles PUT request


## Using proxy
Once all containers are started you can use `proxy-service` by performing one of the following request:

### GET `http://localhost:8080`
Response:
```json

```
### POST `http://localhost:8080`
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
### PUT `http://localhost:8080`
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