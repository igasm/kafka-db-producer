package producer.converters;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class NumberConverter implements SQLTypeConverter{

    @Override
    public String toString(int columnIndex, ResultSet resultSet) throws SQLException {
        Number result;

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        if(resultSetMetaData.getScale(columnIndex) == 0){
            if(resultSetMetaData.getPrecision(columnIndex) > 9){
                result = resultSet.getLong(columnIndex);
            }else{
                result = resultSet.getInt(columnIndex);
            }
        }else{
            result = resultSet.getDouble(columnIndex);
        }

        return result.toString();
    }
}
