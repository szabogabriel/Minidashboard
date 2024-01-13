package com.github.szabogabriel.minidashboard.data.enums;

public enum ConfigurationEnum {

    APPLICATION_NAME("app.name", "Dashboard"),
    ;

    private String key;
    private String defaultValue;

    private ConfigurationEnum(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return defaultValue;
    }
    
}
