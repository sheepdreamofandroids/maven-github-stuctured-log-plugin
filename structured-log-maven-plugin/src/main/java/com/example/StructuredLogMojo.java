package com.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Simple demo mojo that logs a greeting. All Maven output grouping is handled
 * by {@link StructuredLoggingEventSpy} instead of this goal.
 */
@Mojo(name = "hello")
public class StructuredLogMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Hello from structured log plugin!");
    }
}
