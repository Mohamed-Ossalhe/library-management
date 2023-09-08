package org.libraryManagment.config;

import java.util.HashMap;
import java.util.Map;

public class SessionData {
    private static final Map<String, Object> sessionData = new HashMap<>();

    private SessionData() {
        // Private constructor to prevent instantiation
    }

    public static Map<String, Object> getInstance() {
        return sessionData;
    }
}
