package hiof.gruppe25.core.queries.retrievecontrolbysourceandframework;


import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.List;

public class RetrieveControlBySourceAndFrameworkQuery implements iQuery {
    public List<String> sources;
    public List<String> frameworks;
    public RetrieveControlBySourceAndFrameworkQuery(List<String> sources, List<String> frameworks) {
        this.sources = sources;
        this.frameworks = frameworks;
    }

    public RetrieveControlBySourceAndFrameworkQuery() {
    }


}
