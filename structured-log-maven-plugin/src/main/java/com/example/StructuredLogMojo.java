package com.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Mojo that demonstrates bracketing of build output using GitHub Actions
 * group commands. It currently brackets modules and the plugin execution and
 * exposes configuration parameters for future phase support.
 */
@Mojo(name = "hello")
public class StructuredLogMojo extends AbstractMojo {

    /** Bracket output for each module. */
    @Parameter(defaultValue = "true")
    private boolean bracketModules = true;

    /** Bracket output for each lifecycle phase. */
    @Parameter(defaultValue = "true")
    private boolean bracketPhases = true;

    /** Bracket output for each plugin execution. */
    @Parameter(defaultValue = "true")
    private boolean bracketExecutions = true;

    /** Injected project reference for module information. */
    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException {
        if (bracketModules) {
            getLog().info("::group::module: " + project.getName());
        }
        try {
            if (bracketExecutions) {
                getLog().info("::group::plugin: StructuredLogMojo");
            }

            getLog().info("Hello from structured log plugin!");

            if (bracketExecutions) {
                getLog().info("::endgroup::");
            }
            // TODO: bracket lifecycle phases when information becomes available
        } finally {
            if (bracketModules) {
                getLog().info("::endgroup::");
            }
        }
    }

    public boolean isBracketModules() {
        return bracketModules;
    }

    public boolean isBracketPhases() {
        return bracketPhases;
    }

    public boolean isBracketExecutions() {
        return bracketExecutions;
    }
}
