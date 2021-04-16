package liquibase.integrationtest.command

CommandTest.define {
    command = ["migrateSQL"]
    signature = """
Short Description: Write the SQL to deploy changes from the changelog file that have not yet been deployed
Long Description: Write the SQL deploy changes from the changelog file that have not yet been deployed
Required Args:
  url (String) Database URL to generate a changelog for
Optional Args:
  changeLogFile (String) File to write changelog to
    Default: null
  contexts (String) Context string to use for filtering which changes to migrate
    Default: null
  labels (String) Label expression to use for filtering which changes to migrate
    Default: null
  outputFile (String) File for writing the SQL
    Default: null
  password (String) Password to use to connect to the database
    Default: null
  username (String) Username to use to connect to the database
    Default: null
"""

    run {
        arguments = [
                changeLogFile: "changelogs/hsqldb/complete/simple.changelog.xml",
        ]

        expectedResults = [
                statusMessage: "Successfully executed migrateSQL",
                statusCode   : 0
        ]
    }
}