Kafka DB Producer

This is skeleton of application that is supposed to retrieve data from database (especially Oracle DB) and push it to Kafka.

Configuration:
application.properties file allow to choose mode of work:
with application.whitelist it's possible to generate Kafka message topic per table

application.whitelist=ORDERS, CLIENTS

application.query allows to define query that will retrieve data from DB and generate Kafka message for it (only one topic is possible)

application.query=SELECT * FROM ORDERS

database.properties file allows to configure DB connection details as connection url, user, password

Building application:

mvn clean package


Running application:
Before running application, you need to provide JDBC driver for particular database you want to use and set it on application classpath.




