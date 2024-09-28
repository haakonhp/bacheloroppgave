package hiof.gruppe25.core.queries.definitions;

public interface iQueryHandler<ReturnType, TypeOfQuery> {
    ReturnType execute(TypeOfQuery query);
}
