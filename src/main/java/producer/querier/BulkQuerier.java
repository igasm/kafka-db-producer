package producer.querier;

import producer.TopicRecordsQueue;
import producer.converters.ConverterSupplier;
import producer.converters.SQLTypeConverter;
import producer.converters.dialectspecific.ConverterSupplierByClassName;
import producer.records.TopicRecord;

import java.sql.*;

class BulkQuerier implements Querier{

    private final String query;

    BulkQuerier(String query) {
        this.query = query;
    }

    @Override
    public void runQueries(Connection connection, TopicRecordsQueue topicRecordsQueue) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            TopicRecord topicRecord = new TopicRecord("query");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for(int i=1; i<=resultSetMetaData.getColumnCount(); i++){
                topicRecord.addField(resultSetMetaData.getColumnName(i), mapResultSetColumnValueToString(resultSet, i));
            }
            topicRecordsQueue.add(topicRecord);
        }
    }

    private String mapResultSetColumnValueToString(ResultSet resultSet, int columnIndex) throws SQLException {
        String sqlTypeName = resultSet.getMetaData().getColumnTypeName(columnIndex);
        ConverterSupplier converterSupplier = new ConverterSupplier();
        SQLTypeConverter sqlTypeConverter;
        try {
            sqlTypeConverter = converterSupplier.supplyConverter(sqlTypeName);
        } catch (IllegalArgumentException e){
            ConverterSupplierByClassName converterSupplierByClassName = new ConverterSupplierByClassName();
            sqlTypeConverter = converterSupplierByClassName.supplyConverter(resultSet.getMetaData().getColumnClassName(columnIndex));
        }
        return sqlTypeConverter.toString(columnIndex, resultSet);
    }

}
