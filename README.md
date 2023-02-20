## SQS with AWS localstack

SQS listener and producer SpringBoot application, with docker AWS localstack structure.

### Stack

- Java 17
- Gradle 7.6
- AWS Localstack

### How to run

Running localstack image:
```shell
$ docker compose -f docker/docker-compose-dev.yml up -d
```

*This command will run:*

- **localstack** : 4566

Access AWS localstack container:
```shell
$ docker exec -it localstack sh
```

Creating dead letter queue:
```shell
$ aws --endpoint-url http://localhost:4566 sqs create-queue --queue-name my-queue_dlq
```

Creating main queue and associate with dlq after 3 times:
```shell
$ aws --endpoint-url http://localhost:4566 sqs create-queue --queue-name my-queue --attributes '{"RedrivePolicy":"{\"deadLetterTargetArn\":\"arn:aws:sqs:us-east-1:000000000000:my-queue_dlq\",\"maxReceiveCount\":\"3\"}"}'
```

### Manage messages by cli

Accessing localstack container, we can send messages running de the command:

```shell
$ aws --endpoint-url http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/my-queue --message-body '{"key":"val"}'
```

We also can read queue messages running:
```shell
$ aws --endpoint-url http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/my-queue --attribute-names All  --message-attribute-names All  --max-number-of-messages 10
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

- [Swagger](http://localhost:8082/)
