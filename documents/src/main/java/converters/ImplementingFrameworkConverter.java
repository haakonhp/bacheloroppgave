package converters;

import singletons.HeaderSet;
import com.opencsv.bean.AbstractCsvConverter;

import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.models.framework.FrameworkBuilder;
import hiof.gruppe25.core.models.frameworkreference.FrameworkReferenceBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ImplementingFrameworkConverter extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String s) {
        if (Objects.equals(s, "")) {
            return null;
        }
        String[] frameworkAndReference = s.split(";");
        String frameworkName = frameworkAndReference[0];
        List<String> references = Arrays.stream(frameworkAndReference[1].split("\n")).toList();

        HeaderSet.updateHeaders(frameworkName);
        Framework framework = new FrameworkBuilder(-1, frameworkName).build();
        return new FrameworkReferenceBuilder(references, framework).build();
    }
}

