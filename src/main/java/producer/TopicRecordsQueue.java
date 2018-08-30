package producer;

import producer.records.TopicRecord;

import java.util.LinkedList;
import java.util.Queue;

public class TopicRecordsQueue {

    private final Queue<TopicRecord> queue = new LinkedList<>();

    public void add(TopicRecord record){
        queue.add(record);
    }

    public TopicRecord pool(){
        return queue.poll();
    }

    public boolean isEmpty(){
        return queue.peek() == null;
    }

}
