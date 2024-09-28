package hiof.gruppe25.core.queries.retrieveframeworksfilteredbycontrolsources;


import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.List;

public class RetrieveFrameworksFilteredByControlSourcesQuery implements iQuery {
    public List<String> filters;

    public RetrieveFrameworksFilteredByControlSourcesQuery(List<String> filters) {
        this.filters = filters;
    }

    public RetrieveFrameworksFilteredByControlSourcesQuery() {
    }
}
