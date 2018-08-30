package producer.converters.dialectspecific;

import producer.converters.SQLTypeConverter;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OracleArrayConverter implements SQLTypeConverter {
    @Override
    public String toString(int columnIndex, ResultSet resultSet) throws SQLException {
        Array array = resultSet.getArray(columnIndex);
        Object[] arrayItems = (Object[]) array.getArray();
        List<String> itemsList = new ArrayList<>();
        for(int i=0; i<arrayItems.length; i++){
            Struct nestedObject = (Struct) arrayItems[i];
            Object[] nestedObjectFields = nestedObject.getAttributes();
            String fieldsAsString = Arrays.stream(nestedObjectFields).map(obj -> obj.toString()).collect(Collectors.joining(", "));
            itemsList.add("[ " + fieldsAsString + " ]");
        }
        return "{ " + String.join(", ", itemsList) + "} ";
    }
}
