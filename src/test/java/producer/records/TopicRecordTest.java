package producer.records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicRecordTest {

    @Test
    void shouldAllowToCreateTopicRecordWithGivenTopicName(){
        String topicName = "dummyName";
        TopicRecord topicRecord = new TopicRecord(topicName);
        assertEquals(topicName, topicRecord.getTopicName());
    }

    @Test
    void givenTopicRecordWithOneFieldWhenCallingGetRecordAsStringThenStringShouldIncludeOnlyThatField(){
        String topicName = "dummyName";
        TopicRecord topicRecord = new TopicRecord(topicName);
        topicRecord.addField("ID", "1");
        assertEquals("ID: 1", topicRecord.getRecordAsString());
    }

}
