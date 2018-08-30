package producer.converters;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLTypeConverter {

    String toString(int columnIndex, ResultSet resultSet) throws SQLException;

}
