package com.github.szabogabriel.minidashboard.data.gui.renderable;

public class AttributeList {
    private String key;
    private String value;

    public AttributeList(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
