# AGENTS Instructions

- The Structured Log Maven plugin must transform all Maven logging, bracketing module, phase, and plugin execution output in GitHub Actions using `::group` and `::endgroup` commands.
- Configuration parameters must exist for bracketing modules (`bracketModules`), phases (`bracketPhases`), and plugin executions (`bracketExecutions`).
- Before committing, run `mvn -B -ntp verify` and ensure it succeeds.
- The GitHub Actions workflow must install the plugin and run the demo in the same job so the demo uses the freshly built plugin.
- Declare Maven APIs (`maven-plugin-api`, `maven-model`, `maven-artifact`) with `<scope>provided</scope>` in the plugin's POM.
- Keep this AGENTS.md file up to date with any new instructions.
