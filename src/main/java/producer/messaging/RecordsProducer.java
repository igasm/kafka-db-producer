package producer.messaging;

import producer.TopicRecordsQueue;
import producer.records.TopicRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class RecordsProducer {

    private final TopicRecordsQueue topicRecordsQueue;
    private final String topicPrefix;

    public RecordsProducer(String topicPrefix, TopicRecordsQueue topicRecordsQueue) {
        this.topicRecordsQueue = topicRecordsQueue;
        this.topicPrefix = topicPrefix;
    }

    private static Producer<String, String> createKafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaDBProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    public void run() throws ExecutionException, InterruptedException {
        final Producer<String, String> kafkaProducer = createKafkaProducer();
        try {
            TopicRecord topicRecord;
            while ((topicRecord = topicRecordsQueue.pool()) != null) {
                sendKafkaMessage(topicRecord, kafkaProducer);
            }
        }finally {
            kafkaProducer.flush();
            kafkaProducer.close();
        }
    }

    private void sendKafkaMessage(TopicRecord topicRecord, Producer<String, String> kafkaProducer) throws ExecutionException, InterruptedException {
        final ProducerRecord<String, String> kafkaRecord = new ProducerRecord<>(topicPrefix + topicRecord.getTopicName(), topicRecord.getRecordAsString());
        RecordMetadata recordMetadata = kafkaProducer.send(kafkaRecord).get();
        System.out.println("Kafka message send: " + kafkaRecord.value() + ", topic: " + recordMetadata.topic());
    }


}
