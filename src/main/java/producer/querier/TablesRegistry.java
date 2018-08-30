package producer.querier;

import java.util.*;

class TablesRegistry {

    private final Set<String> tables;

    TablesRegistry(List<String> tablesNames) {
        this.tables = new HashSet<>(tablesNames);
    }

    List<String> getTablesNames(){
        return new ArrayList<>(tables);
    }

}
