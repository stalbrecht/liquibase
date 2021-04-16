package liquibase.integrationtest.setup

import liquibase.Liquibase
import liquibase.changelog.ChangeLogHistoryService
import liquibase.changelog.ChangeLogHistoryServiceFactory
import liquibase.database.Database
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.integration.commandline.CommandLineResourceAccessor
import liquibase.integrationtest.TestDatabaseConnections
import liquibase.integrationtest.TestSetup
import liquibase.resource.CompositeResourceAccessor
import liquibase.resource.FileSystemResourceAccessor

import java.nio.file.Paths

class SetupRunChangelog extends TestSetup {

    private final String changeLog

    SetupRunChangelog(String changeLog) {
        this.changeLog = changeLog
    }

    @Override
    void setup(TestDatabaseConnections.ConnectionStatus connectionStatus) throws Exception {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connectionStatus.connection))

        final ChangeLogHistoryService changeLogService = ChangeLogHistoryServiceFactory.getInstance().getChangeLogService(database)
        changeLogService.init()
        changeLogService.generateDeploymentId()

        changeLogService.reset()
        CompositeResourceAccessor fileOpener = new CompositeResourceAccessor(
                new FileSystemResourceAccessor(Paths.get(".").toAbsolutePath().toFile()),
                new CommandLineResourceAccessor(getClass().getClassLoader())
        )
        Liquibase liquibase = new Liquibase(this.changeLog, fileOpener, database)
        liquibase.update((String) null)
    }
}