# AGENTS Instructions

- The Structured Log Maven plugin should bracket module, phase, and plugin execution output in GitHub Actions using `::group` and `::endgroup` commands.
- Configuration parameters must exist for bracketing modules (`bracketModules`), phases (`bracketPhases`), and plugin executions (`bracketExecutions`).
- Before committing, run `mvn -B -ntp verify` and ensure it succeeds.
- Keep this AGENTS.md file up to date with any new instructions.
