package producer.records;

import java.util.*;

class Record {

    private final Map<String, String> data;

    Record() {
        data = new HashMap<>();
    }

    void addField(String name, String value){
        data.put(name, value);
    }

    @Override
    public String toString(){
       StringBuilder stringBuilder = new StringBuilder();
       for(Map.Entry<String, String> pair : data.entrySet()){
           stringBuilder.append(pair.getKey() + ": " + pair.getValue() + ", ");
       }
       if(stringBuilder.length() > 0) {
           stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());
       }
       return stringBuilder.toString();
    }


}
