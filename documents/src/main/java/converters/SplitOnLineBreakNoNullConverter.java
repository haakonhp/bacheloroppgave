package converters;

import com.opencsv.bean.AbstractCsvConverter;

import java.util.Objects;

public class SplitOnLineBreakNoNullConverter extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String s) {
        if (Objects.equals(s, "")) {
            return null;
        }
        return s;
    }
}
