package singletons;

import hiof.gruppe25.core.models.source.Source;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SourceSet {
    private static final Set<String> sources = Collections.synchronizedSet(new HashSet<>());
    public static void updateHeaders(String source) {
        sources.add(source);
    }
    public static void initialize() {
        sources.clear();
    }
    public static List<Source> getSources() {
        return sources.stream().map(Source::new).toList();
    }
}
