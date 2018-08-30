package producer.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterSupplierTest {

    private ConverterSupplier converterSupplier;

    @BeforeEach
    void setUp(){
        converterSupplier = new ConverterSupplier();
    }

    @ParameterizedTest
    @ValueSource(strings = {"NUMBER", "VARCHAR2"})
    void givenSQLTypeNameWhichIsSupportedWhenSupplyingConverterThenReturnsProperConverter(String sqlTypeName){
        SQLTypeConverter converter = converterSupplier.supplyConverter(sqlTypeName);
        assertTrue(converter.getClass().getName().toUpperCase().contains(sqlTypeName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"dummySQLType1", "dummySQLType2"})
    void givenSQLTypeNameWhichIsNotSupportedWhenSupplyingConverterThenThrowsIllegalArgumentException(String sqlTypeName){
        assertThrows(IllegalArgumentException.class,
                () -> converterSupplier.supplyConverter(sqlTypeName));
    }

}