## SQS Localstack

An consumer and producer sqs using localstack and spting boot API.

### Stack

- Java 17
- Gradle 7.6
- Docker

### How to run

#### Locally, only dependencies

Run docker compose command:

````shell
$ docker compose docker/docker-compose-dev -up -d
````
*Will be executed localstack on port 4566*

After this, access localstack container

````shell
$ docker exec -it localstack sh
````

Creating a queue...

```shell
$ aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name my-queue
```
### Running the application

```shell
$ ./gradlew bootRun
```

## Sending message

Sending message to topic by HTTP request:

````shell
curl -X 'POST' \
  'http://localhost:8082/sqs/sendMessage' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "user-notify",
  "xRequestId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "idempotency_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "dispatchedAt": "2023-02-13T20:56:15.448Z",
  "userDTO": {
    "name": "Naruto Uzumaki",
    "document": "32243434A",
    "age": 21
  }
}'
````

## Receiving message
 
All messages sent are logged in application output

```shell
2023-02-13 18:13:23.471  INFO 44495 --- [enerContainer-2] c.l.sqslocalstack.listener.SQSListener   : SQS Message Received : MessageDTO[name=user-notify, xRequestId=3fa85f64-5717-4562-b3fc-2c963f66afa6, idempotency_id=3fa85f64-5717-4562-b3fc-2c963f66afa6, dispatchedAt=Mon Feb 13 17:56:15 BRT 2023, userDTO=UserDTO[name=Joaozinho, document=32243434A, age=21]]
```

## Documentation

- [Swagger](http://localhost:8082/swagger-ui/index.html)
