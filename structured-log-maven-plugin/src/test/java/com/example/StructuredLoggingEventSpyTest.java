package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.junit.jupiter.api.Test;

public class StructuredLoggingEventSpyTest {

    @Test
    public void testDefaults() throws Exception {
        StructuredLoggingEventSpy spy = new StructuredLoggingEventSpy();
        EventSpy.Context ctx = new EventSpy.Context();
        ctx.getData().put("executionRequest", new DefaultMavenExecutionRequest());
        spy.init(ctx);
        assertTrue(spy.isBracketModules());
        assertTrue(spy.isBracketPhases());
        assertTrue(spy.isBracketExecutions());
    }

    @Test
    public void testOverrides() throws Exception {
        StructuredLoggingEventSpy spy = new StructuredLoggingEventSpy();
        EventSpy.Context ctx = new EventSpy.Context();
        DefaultMavenExecutionRequest req = new DefaultMavenExecutionRequest();
        Properties props = new Properties();
        props.setProperty("structuredLog.bracketModules", "false");
        props.setProperty("structuredLog.bracketPhases", "false");
        props.setProperty("structuredLog.bracketExecutions", "false");
        req.setUserProperties(props);
        ctx.getData().put("executionRequest", req);
        spy.init(ctx);
        assertFalse(spy.isBracketModules());
        assertFalse(spy.isBracketPhases());
        assertFalse(spy.isBracketExecutions());
    }
}
