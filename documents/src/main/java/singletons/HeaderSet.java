package singletons;

import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.models.framework.FrameworkBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HeaderSet {
    private static final Set<String> frameworks = Collections.synchronizedSet(new HashSet<>());
    public static void updateHeaders(String framework) {
        frameworks.add(framework);
    }

    public static void initialize() {
        frameworks.clear();
    }
    public static List<Framework> getFrameworks() {
        FrameworkBuilder builder = new FrameworkBuilder();

        return frameworks.stream().map((framework) -> {
            builder.setFramework(framework);
            return builder.build();
        }).toList();
    }
}
