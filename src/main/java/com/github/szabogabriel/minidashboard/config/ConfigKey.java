package com.github.szabogabriel.minidashboard.config;

import java.util.regex.Pattern;

public class ConfigKey {

    private String key;

    private Pattern pattern = null;

    public ConfigKey(String key) {
        this.key = key;
        if (hasWildcard(key)) {
            pattern = Pattern.compile(createRegex(key));
        }
    }

    public String getConfigKey() {
        return key;
    }

    private boolean hasWildcard(String key) {
        return key.contains("*");
    }

    private String createRegex(String key) {
        return key.replaceAll("\\*", ".*");
    }

    public boolean matches(String key) {
        boolean ret = false;
        if (pattern != null) {
            ret = pattern.matcher(key).matches();
        } else {
            ret = this.key.equals(key);
        }
        return ret;
    }

    public boolean matches(ConfigKey key) {
        if (key != null && key.key != null) {
            return matches(key.key);
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConfigKey other = (ConfigKey) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

    
}
