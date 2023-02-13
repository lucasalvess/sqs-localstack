## SQS Localstack

### Requirements

- Java 17
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

