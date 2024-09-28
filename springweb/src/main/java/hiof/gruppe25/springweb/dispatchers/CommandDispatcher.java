package hiof.gruppe25.springweb.dispatchers;

import hiof.gruppe25.core.commands.definitions.iCommand;
import hiof.gruppe25.core.commands.definitions.iCommandHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandDispatcher {
    static Map<Class<?>, Class<?>> handlers = new HashMap<>();
    private final Function<Class<?>, iCommandHandler> instanceInterface;

    public CommandDispatcher(Function<Class<?>, iCommandHandler> instanceInterface) {
        this.instanceInterface = instanceInterface;
    }

    public static void register(Class<?> commandClass, Class<?> handlerClass) {
        handlers.put(commandClass, handlerClass);
    }
    public void execute(iCommand command) throws Exception {
        Class<?> handlerType = handlers.get(command.getClass());
        iCommandHandler handler = instanceInterface.apply(handlerType);
        handler.execute(command);
    }

}
