package producer.converters;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConverterSupplier {

    private static final Map<String, Supplier<SQLTypeConverter>> converterSuppliersByTypeName;

    static {
        final Map<String, Supplier<SQLTypeConverter>> converters = new HashMap<>();
        converters.put("NUMBER", NumberConverter::new);
        converters.put("VARCHAR2", Varchar2Converter::new);
        converters.put("DATE", DateConverter::new);
        converters.put("TIMESTAMP WITH TIME ZONE", TimestampConverter::new);
        converterSuppliersByTypeName = Collections.unmodifiableMap(converters);
    }

    public SQLTypeConverter supplyConverter(String sqlTypeName){
        Supplier<SQLTypeConverter> supplier = converterSuppliersByTypeName.get(sqlTypeName);
        if(supplier == null){
            throw new IllegalArgumentException("SQL type " + sqlTypeName + " not supported");
        }
        return supplier.get();
    }

}
