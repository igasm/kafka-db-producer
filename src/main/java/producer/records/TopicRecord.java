package producer.records;

public class TopicRecord {

    private final String topicName;
    private final Record record;

    public TopicRecord(String topicName) {
        this.topicName = topicName;
        this.record = new Record();
    }

    public String getTopicName(){
        return topicName;
    }

    public String getRecordAsString(){
        return record.toString();
    }

    public void addField(String name, String value) {
        record.addField(name, value);
    }
}
