package hiof.gruppe25.springweb.dispatchers;

import hiof.gruppe25.core.queries.definitions.iQuery;
import hiof.gruppe25.core.queries.definitions.iQueryHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class QueryDispatcher {
    static Map<Class<?>, Class<?>> handlers = new HashMap<>();
    private final Function<Class<?>, iQueryHandler<?,?>> instanceInterface;

    public QueryDispatcher(Function<Class<?>, iQueryHandler<?,?>> instanceInterface) {
        this.instanceInterface = instanceInterface;
    }

    public static void register(Class<?> commandClass, Class<?> handlerClass) {
        handlers.put(commandClass, handlerClass);
    }
    public <T> T query(iQuery query) throws Exception {
        Class<?> handlerType = handlers.get(query.getClass());
        iQueryHandler handler = instanceInterface.apply(handlerType);
        return (T)handler.execute(query);
    }

}
