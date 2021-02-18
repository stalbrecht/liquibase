package liquibase.command;

import liquibase.exception.CommandArgumentValidationException;

import java.util.SortedSet;

/**
 * Commands are higher-level functions. They may execute multiple {@link liquibase.change.Change}s and usually interact with the outside world.
 * Commands are different from Actions in that they implement end-user functionality rather than small pieces of logic.
 * We package functionaly as commands so that the command line interface as well as other integrations can all use the same business logic.
 */
public interface LiquibaseCommand {

    int ORDER_DEFAULT = 1000;
    int ORDER_NOT_APPLICABLE = -1;

    String[] getName();

    int getOrder(CommandScope commandName);

    void validate(CommandScope commandScope) throws CommandArgumentValidationException;

    /**
     * Function that performs the actual logic. This should not be called directly by any code,
     * only by {@link CommandFactory#execute(LiquibaseCommand)}
     * @param commandScope
     */
    void run(CommandScope commandScope) throws Exception;
}
