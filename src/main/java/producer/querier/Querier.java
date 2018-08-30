package producer.querier;

import producer.TopicRecordsQueue;

import java.sql.Connection;
import java.sql.SQLException;

public interface Querier {

    void runQueries(Connection connection, TopicRecordsQueue topicRecordsQueue) throws SQLException;

}
