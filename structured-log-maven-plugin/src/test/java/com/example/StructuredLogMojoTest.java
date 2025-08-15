package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StructuredLogMojoTest {
    @Test
    public void testMojoDefaults() {
        StructuredLogMojo mojo = new StructuredLogMojo();
        assertNotNull(mojo);
        assertTrue(mojo.isBracketModules());
        assertTrue(mojo.isBracketPhases());
        assertTrue(mojo.isBracketExecutions());
    }
}
