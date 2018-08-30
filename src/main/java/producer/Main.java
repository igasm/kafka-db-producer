package producer;

import producer.messaging.RecordsProducer;
import producer.querier.Querier;
import producer.querier.QuerierProvider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Main {

    private static final String topicPrefix = "dbProducer-";

    public static void main(String[] args) {
        try {
            Querier querier = initQuerierMode();
            TopicRecordsQueue topicRecordsQueue = new TopicRecordsQueue();
            try(Connection conn = getConnection()) {
                querier.runQueries(conn, topicRecordsQueue);
            }
            RecordsProducer recordsProducer = new RecordsProducer(topicPrefix, topicRecordsQueue);
            recordsProducer.run();
        } catch (SQLException | IOException | InterruptedException | ExecutionException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Properties properties = new Properties();

        try(InputStream inputStream = Main.class.getResourceAsStream("/database.properties")){
            properties.load(inputStream);
        }

        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        return DriverManager.getConnection(url, user, password);
    }

    public static Querier initQuerierMode() throws IOException {
        Properties properties = new Properties();

        try(InputStream inputStream = Main.class.getResourceAsStream("/application.properties")){
            properties.load(inputStream);
        }

        QuerierProvider querierProvider = new QuerierProvider();

        if(properties.getProperty("application.whitelist") != null){
            String whitelist = properties.getProperty("application.whitelist");
            return querierProvider.byWhitelist(whitelist.split(","));
        }else if(properties.getProperty("application.query") != null){
            return querierProvider.byQuery(properties.getProperty("application.query"));
        }else{
            throw new RuntimeException("property application.whitelist or application.query is required");
        }

    }

}
