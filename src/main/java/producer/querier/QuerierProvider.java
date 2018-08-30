package producer.querier;

import java.util.Arrays;

public class QuerierProvider {

    public Querier byQuery(String query){
        return new BulkQuerier(query);
    }

    public Querier byWhitelist(String[] whitelist){
        TablesRegistry tablesRegistry = new TablesRegistry(Arrays.asList(whitelist));
        return new TableQuerier(tablesRegistry);
    }

}
