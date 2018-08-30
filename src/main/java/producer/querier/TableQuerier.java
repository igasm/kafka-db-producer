package producer.querier;

import producer.converters.ConverterSupplier;
import producer.records.TopicRecord;
import producer.TopicRecordsQueue;
import producer.converters.SQLTypeConverter;

import java.sql.*;

class TableQuerier implements Querier {

    private final TablesRegistry tablesRegistry;

    TableQuerier(TablesRegistry tablesRegistry) {
        this.tablesRegistry = tablesRegistry;
    }

    @Override
    public void runQueries(Connection connection, TopicRecordsQueue topicRecordsQueue) throws SQLException {
        for(String tableName : tablesRegistry.getTablesNames()){
            queryTable(tableName, connection, topicRecordsQueue);
        }
    }

    private TopicRecordsQueue queryTable(String tableName, Connection connection, TopicRecordsQueue topicRecordsQueue) throws SQLException {

        String rowQuery = "SELECT * FROM " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(rowQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            TopicRecord topicRecord = new TopicRecord(tableName);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for(int i=1; i<=resultSetMetaData.getColumnCount(); i++){
                topicRecord.addField(resultSetMetaData.getColumnName(i), mapResultSetColumnValueToString(resultSet, i));
            }
            topicRecordsQueue.add(topicRecord);
        }

        return topicRecordsQueue;
    }

    private String mapResultSetColumnValueToString(ResultSet resultSet, int columnIndex) throws SQLException {
        String sqlTypeName = resultSet.getMetaData().getColumnTypeName(columnIndex);
        ConverterSupplier converterSupplier = new ConverterSupplier();
        SQLTypeConverter sqlTypeConverter = converterSupplier.supplyConverter(sqlTypeName);
        return sqlTypeConverter.toString(columnIndex, resultSet);
    }

}
