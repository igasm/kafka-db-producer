# Kafka DB Producer

This is skeleton of application that is supposed to retrieve data from database (especially Oracle DB) and push it to Kafka.

## Configuration
### application.properties
application.properties file allows to choose mode of work:
* with application.whitelist it's possible to generate Kafka message topic per table
example:
        ```
        application.whitelist=ORDERS, CLIENTS
        ```

* application.query allows to define query that will retrieve data from DB and generate Kafka message for it (only one topic is possible)
example:
        ```
        application.query=SELECT * FROM ORDERS
        ```

### database.properties
This file allows to configure DB connection details as connection url, user, password.
Default settings allows to connect to Oracle DB created with provided docker-compose.yml file (in /docker folder).

## Using docker-compose file
With provided docker-compose.yml file, containers with Oracle XE 11g and Kafka (Landoop environment) can be easily created.
To create them go to docker folder:
```
docker-compose up
```

to stop running container use (again from docker folder):
```
docker-compose down
```

Settings for connecting database from localhost:
hostname: localhost
port: 1521
sid: xe
username: demo
password: demo

Connecting with Kafka container terminal:
```
docker exec -ti kafka-service bash
```

Reading Kafka messages by topic (from Kafka container terminal)
```
kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic dbProducer-ORDERS --from-beginning
```


## Running application
Before running application, you need to provide JDBC driver for particular database you want to use and set it on application classpath.



