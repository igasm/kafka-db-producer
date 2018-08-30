package producer.converters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DateConverter implements SQLTypeConverter{
    @Override
    public String toString(int columnIndex, ResultSet resultSet) throws SQLException {
        return resultSet.getDate(columnIndex).toString();
    }
}
