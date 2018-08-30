package producer.records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordTest {

    @Test
    void givenNoFieldsWhenCallingToStringThenResultShouldBeEmptyString(){
        //given
        Record record = new Record();
        //when
        String actual = record.toString();
        //then
        assertEquals("", actual);
    }

    @Test
    void givenTwoFieldsWhenCallingToStringThenResultShouldContainsThemOnly(){
        //given
        Record record = new Record();
        record.addField("ID", "1");
        record.addField("ITEM", "stocks");
        //when
        String actual = record.toString();
        //then
        assertEquals("ITEM: stocks, ID: 1", actual);
    }

    @Test
    void whenAddingTheSameFieldAgainThenNoDuplicatesWillExist(){
        //given
        Record record = new Record();
        //when
        record.addField("ID", "1");
        record.addField("ID", "1");
        //then
        assertEquals("ID: 1", record.toString());
    }

    @Test
    void givenOneFieldInRecordWhenAddingFieldWithSameNameAndDifferentValueThenDataInRecordShouldBeOverwrittenWithSecondAddedValue(){
        //given
        Record record = new Record();
        record.addField("ID", "1");
        //when
        record.addField("ID", "2");
        //then
        assertEquals("ID: 2", record.toString());
    }

}