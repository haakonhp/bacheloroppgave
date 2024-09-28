package hiof.gruppe25.core.queries.retrievecorporationbyid;

import hiof.gruppe25.core.queries.definitions.iQuery;

import java.util.UUID;

public class RetrieveCorporationByIdQuery implements iQuery {
    public UUID id;

    public RetrieveCorporationByIdQuery(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
