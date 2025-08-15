# maven-github-stuctured-log-plugin

This repository hosts a minimal Maven plugin demonstrating structured logging
in GitHub Actions. The plugin exposes a single `hello` goal that prints a simple
message and is used by the included demo project. The goal outputs GitHub
Actions group commands around modules and plugin executions, laying the
groundwork for more fine‑grained phase grouping.

Two GitHub Actions workflows are provided:

* **Build** – compiles and tests the plugin on every push and pull request.
* **Demo** – runs after a successful build and executes the demo project using
the freshly built plugin.

To build everything locally run:

```bash
mvn -B -ntp verify
```

## Usage

Add the plugin to your project and configure the grouping parameters as needed:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.example</groupId>
      <artifactId>structured-log-maven-plugin</artifactId>
      <version>1.0-SNAPSHOT</version>
      <executions>
        <execution>
          <goals>
            <goal>hello</goal>
          </goals>
        </execution>
      </executions>
      <configuration>
        <bracketModules>true</bracketModules>
        <bracketPhases>true</bracketPhases>
        <bracketExecutions>true</bracketExecutions>
      </configuration>
    </plugin>
  </plugins>
</build>
```

Parameters:

| Parameter | Default | Description |
|-----------|---------|-------------|
| `bracketModules` | `true` | Wrap each module's output in a group |
| `bracketPhases` | `true` | (Planned) Wrap each lifecycle phase in a group |
| `bracketExecutions` | `true` | Wrap each plugin execution in a group |
