package liquibase.command.core;

import liquibase.command.*;
import liquibase.integration.commandline.Main;

public class HistoryCommandStep extends AbstractCliWrapperCommandStep {
    public static final CommandArgumentDefinition<String> URL_ARG;
    public static final CommandArgumentDefinition<String> USERNAME_ARG;
    public static final CommandArgumentDefinition<String> PASSWORD_ARG;
    public static final CommandArgumentDefinition<String> OUTPUT_FILE_ARG;

    static {
        CommandStepBuilder builder = new CommandStepBuilder(HistoryCommandStep.class);
        URL_ARG = builder.argument("url", String.class).required()
            .description("The JDBC database connection URL").build();
        USERNAME_ARG = builder.argument("username", String.class)
            .description("Username to use to connect to the database").build();
        PASSWORD_ARG = builder.argument("password", String.class)
            .description("Password to use to connect to the database").build();
        OUTPUT_FILE_ARG = builder.argument("changeLogFile", String.class)
            .description("File to write changelog to").build();
    }

    @Override
    public String[] getName() {
        return new String[] {"history"};
    }

    @Override
    public void run(CommandResultsBuilder resultsBuilder) throws Exception {
        CommandScope commandScope = resultsBuilder.getCommandScope();

        String[] args = createArgs(commandScope);
        int statusCode = Main.run(args);
        addStatusMessage(resultsBuilder, statusCode);
        resultsBuilder.addResult("statusCode", statusCode);
    }

    @Override
    public void adjustCommandDefinition(CommandDefinition commandDefinition) {
        commandDefinition.setShortDescription("List all deployed changesets and their deployment ID");
        commandDefinition.setLongDescription("List all deployed changesets and their deployment ID");
    }
}