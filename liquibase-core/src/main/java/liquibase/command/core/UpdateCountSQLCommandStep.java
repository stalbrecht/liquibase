package liquibase.command.core;

import liquibase.command.*;
import liquibase.integration.commandline.Main;

public class UpdateCountSQLCommandStep extends AbstractCliWrapperCommandStep {
    public static final CommandArgumentDefinition<String> CHANGELOG_FILE_ARG;
    public static final CommandArgumentDefinition<String> URL_ARG;
    public static final CommandArgumentDefinition<String> LABELS_ARG;
    public static final CommandArgumentDefinition<String> CONTEXTS_ARG;
    public static final CommandArgumentDefinition<Integer> COUNT_ARG;

    static {
        CommandStepBuilder builder = new CommandStepBuilder(UpdateCountSQLCommandStep.class);
        CHANGELOG_FILE_ARG = builder.argument("changeLogFile", String.class).required().build();
        URL_ARG = builder.argument("url", String.class).required().build();
        LABELS_ARG = builder.argument("labels", String.class).build();
        CONTEXTS_ARG = builder.argument("contexts", String.class).build();
        COUNT_ARG = builder.argument("count", Integer.class).build();
    }

    @Override
    public String[] getName() {
        return new String[] {"updateCountSQL"};
    }

    @Override
    public void run(CommandResultsBuilder resultsBuilder) throws Exception {
        CommandScope commandScope = resultsBuilder.getCommandScope();

        String[] args = createParametersFromArgs(createArgs(commandScope), "count");
        int statusCode = Main.run(args);
        addStatusMessage(resultsBuilder, statusCode);
        resultsBuilder.addResult("statusCode", statusCode);
    }

}