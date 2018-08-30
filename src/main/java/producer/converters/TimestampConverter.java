package producer.converters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimestampConverter implements SQLTypeConverter {
    @Override
    public String toString(int columnIndex, ResultSet resultSet) throws SQLException {
        return resultSet.getTimestamp(columnIndex).toString();
    }
}
