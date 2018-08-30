package producer;

import producer.records.TopicRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicRecordsQueueTest {

    private TopicRecordsQueue topicRecordsQueue;

    @BeforeEach
    void setUp(){
        topicRecordsQueue = new TopicRecordsQueue();
    }

    @Test
    void givenOneRecordInQueueWhenCallingPollThenRecordShouldBeReturned(){
        //given
        TopicRecord record = new TopicRecord("someName");
        topicRecordsQueue.add(record);
        //when - then
        assertEquals(record, topicRecordsQueue.pool());
    }

    @Test
    void whenCallingIsEmptyThenShouldReturnTrue(){
        assertEquals(topicRecordsQueue.isEmpty(), true);
    }

    @Test
    void givenOneRecordInQueueWhenCallingIsEmptyThenShouldReturnFalse(){
        //given
        TopicRecord record = new TopicRecord("someName");
        topicRecordsQueue.add(record);
        //when - then
        assertEquals(topicRecordsQueue.isEmpty(), false);
    }

}