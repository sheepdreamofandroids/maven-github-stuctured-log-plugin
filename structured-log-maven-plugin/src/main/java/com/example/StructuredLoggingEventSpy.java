package com.example;

import java.util.Properties;

import org.apache.maven.eventspy.AbstractEventSpy;
import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.MavenExecutionRequest;

/**
 * Event spy that wraps Maven logging with GitHub Actions group commands for
 * modules, lifecycle phases and plugin executions.
 */
public class StructuredLoggingEventSpy extends AbstractEventSpy {

    private boolean bracketModules = true;
    private boolean bracketPhases = true;
    private boolean bracketExecutions = true;
    private String currentPhase;

    @Override
    public void init(Context context) throws Exception {
        Object requestObj = context.getData().get("executionRequest");
        if (requestObj instanceof MavenExecutionRequest request) {
            Properties props = request.getUserProperties();
            bracketModules = Boolean.parseBoolean(props.getProperty("structuredLog.bracketModules", "true"));
            bracketPhases = Boolean.parseBoolean(props.getProperty("structuredLog.bracketPhases", "true"));
            bracketExecutions = Boolean.parseBoolean(props.getProperty("structuredLog.bracketExecutions", "true"));
        }
    }

    @Override
    public void onEvent(Object event) throws Exception {
        if (!(event instanceof ExecutionEvent ee)) {
            return;
        }
        switch (ee.getType()) {
            case ProjectStarted:
                currentPhase = null;
                if (bracketModules) {
                    System.out.println("::group::module " + ee.getProject().getName());
                }
                break;
            case ProjectSucceeded:
            case ProjectFailed:
                if (bracketModules) {
                    closePhaseGroup();
                    System.out.println("::endgroup::");
                }
                break;
            case MojoStarted:
                if (bracketPhases) {
                    String phase = ee.getMojoExecution().getLifecyclePhase();
                    if (phase != null && !phase.equals(currentPhase)) {
                        closePhaseGroup();
                        currentPhase = phase;
                        System.out.println("::group::phase " + phase);
                    }
                }
                if (bracketExecutions) {
                    String exec = ee.getMojoExecution().getArtifactId() + ":" + ee.getMojoExecution().getGoal();
                    System.out.println("::group::execution " + exec);
                }
                break;
            case MojoSucceeded:
            case MojoFailed:
                if (bracketExecutions) {
                    System.out.println("::endgroup::");
                }
                break;
            default:
                break;
        }
    }

    private void closePhaseGroup() {
        if (currentPhase != null && bracketPhases) {
            System.out.println("::endgroup::");
            currentPhase = null;
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
