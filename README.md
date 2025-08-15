# maven-github-stuctured-log-plugin

This repository hosts a Maven extension that rewrites Maven's log output with
GitHub Actions group commands. All module, lifecycle phase, and plugin execution
output is wrapped in `::group`/`::endgroup` markers to create collapsible
sections in workflow logs. A simple `hello` goal remains for demo purposes, but
the grouping is handled globally by an EventSpy.

A single GitHub Actions workflow installs the plugin and executes the demo
project on every push and pull request so the demo runs with the latest build.

To build everything locally run the plugin and demo separately:

```bash
mvn -B -ntp -f structured-log-maven-plugin/pom.xml install
mvn -B -ntp -f demo/pom.xml verify
```

## Usage

Register the extension in your build so it can intercept Maven events:

```xml
<build>
  <extensions>
    <extension>
      <groupId>com.example</groupId>
      <artifactId>structured-log-maven-plugin</artifactId>
      <version>1.0-SNAPSHOT</version>
    </extension>
  </extensions>
</build>
```

Grouping can be toggled with system properties:

```bash
mvn -DstructuredLog.bracketModules=false \
    -DstructuredLog.bracketPhases=false \
    -DstructuredLog.bracketExecutions=false verify
```

Each property defaults to `true` and controls whether modules, phases, or plugin
executions are wrapped in groups.
