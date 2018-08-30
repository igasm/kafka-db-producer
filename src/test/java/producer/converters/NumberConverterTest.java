package producer.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberConverterTest {

    NumberConverter numberConverter;

    @BeforeEach
    void setUp(){
        numberConverter = new NumberConverter();
    }

    ResultSet prepareResultSetMock(int columnIndex, int scale, int precison) throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        ResultSetMetaData metaData = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(resultSetMock.getMetaData()).thenReturn(metaData);
        Mockito.when(metaData.getScale(columnIndex)).thenReturn(scale);
        Mockito.when(metaData.getPrecision(columnIndex)).thenReturn(precison);
        return resultSetMock;
    }

    @Test
    void givenResultSetWithColumnOfTypeNumberWithScale0AndPrecisionGreaterThen9ShouldReturnStringRepresentationOfNumber() throws SQLException {
        //given
        int columnIndex = 1;
        Long value = 1234567890L;
        ResultSet resultSetMock = prepareResultSetMock(columnIndex, 0, 10);
        Mockito.when(resultSetMock.getLong(columnIndex)).thenReturn(value);
        //when
        String stringValue = numberConverter.toString(columnIndex, resultSetMock);
        //then
        assertEquals(value.toString(), stringValue);
    }


}