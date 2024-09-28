package hiof.gruppe25.core.persistenceinterfaces;

import hiof.gruppe25.core.models.control.Control;

import java.util.List;

public interface iControlRepository extends iRepository<Control> {
    List<Control> getWithPriorityOver(int priority);
    List<String> getDistinctSources();
    List<Control> getFilteredBySources(List<String> sources);
    List<Control> getFilteredBySourcesAndFrameworks(List<String> sources, List<String> frameworks);
}
