package producer.converters;

import java.sql.ResultSet;
import java.sql.SQLException;

class Varchar2Converter implements SQLTypeConverter {
    @Override
    public String toString(int columnIndex, ResultSet resultSet) throws SQLException {
        return resultSet.getString(columnIndex);
    }
}
