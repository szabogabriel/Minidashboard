package com.github.szabogabriel.minidashboard.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConfigKeyTest {

    @Test
    void testCreateBasicStringWithoutPattern() {
        ConfigKey ck = new ConfigKey("Test");

        assertTrue(ck.matches("Test"));
        assertFalse(ck.matches("Else"));
    }

    @Test
    void testCreateBasicStringWIthPattern() {
        // ConfigKey ck = new ConfigKey("/test/*");

        // assertTrue(ck.matches("/test/anything"));
        // assertTrue(ck.equals(new ConfigKey("/test/anything")));

        // ck = new ConfigKey("/test/*/alfa");

        // assertTrue(ck.matches("/test/beta/alfa"));
        // assertTrue(ck.equals(new ConfigKey("/test/beta/alfa")));
    }

}
