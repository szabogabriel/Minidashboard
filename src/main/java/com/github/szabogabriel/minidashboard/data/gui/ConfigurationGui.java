package com.github.szabogabriel.minidashboard.data.gui;

import java.util.Objects;

public class ConfigurationGui {

    private String key;
    private String value;

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ConfigurationGui)) return false;
        ConfigurationGui that = (ConfigurationGui) other;
        return that.canEqual(this) && Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConfigurationGui;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "ConfigurationGui(key=" + key + ", value=" + value + ")";
    }
}
