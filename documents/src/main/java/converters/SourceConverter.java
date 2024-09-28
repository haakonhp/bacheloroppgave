package converters;

import singletons.SourceSet;
import com.opencsv.bean.AbstractBeanField;

public class SourceConverter extends AbstractBeanField {
    @Override
    protected Object convert(String s) {
        SourceSet.updateHeaders(s);
        return s;
    }
}

