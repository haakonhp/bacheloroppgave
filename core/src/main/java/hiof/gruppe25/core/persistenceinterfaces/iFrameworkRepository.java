package hiof.gruppe25.core.persistenceinterfaces;

import hiof.gruppe25.core.models.framework.Framework;

import java.util.List;

public interface iFrameworkRepository extends iRepository<Framework> {
    List<String> getDistinctFrameworksByFilteredSources(List<String> sources);
}
