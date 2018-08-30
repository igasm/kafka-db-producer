package producer.converters.dialectspecific;

import producer.converters.SQLTypeConverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConverterSupplierByClassName {

    private static final Map<String, Supplier<SQLTypeConverter>> converterSuppliersByClassName;

    static {
        final Map<String, Supplier<SQLTypeConverter>> converters = new HashMap<>();
        converters.put("oracle.jdbc.OracleArray", OracleArrayConverter::new);
        converterSuppliersByClassName = Collections.unmodifiableMap(converters);
    }

    public SQLTypeConverter supplyConverter(String sqlClassName){
        Supplier<SQLTypeConverter> supplier = converterSuppliersByClassName.get(sqlClassName);
        if(supplier == null){
            throw new IllegalArgumentException("SQL type " + sqlClassName + " not supported");
        }
        return supplier.get();
    }
}
