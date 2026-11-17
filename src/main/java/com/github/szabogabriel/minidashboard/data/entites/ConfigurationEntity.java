package com.github.szabogabriel.minidashboard.data.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long config_id;

    private String confKey;

    private String confValue;
    
    public Long getConfig_id() { return config_id; }
    public void setConfig_id(Long config_id) { this.config_id = config_id; }
    public String getConfKey() { return confKey; }
    public void setConfKey(String confKey) { this.confKey = confKey; }
    public String getConfValue() { return confValue; }
    public void setConfValue(String confValue) { this.confValue = confValue; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ConfigurationEntity)) return false;
        ConfigurationEntity that = (ConfigurationEntity) other;
        return that.canEqual(this)
                && Objects.equals(config_id, that.config_id)
                && Objects.equals(confKey, that.confKey)
                && Objects.equals(confValue, that.confValue);
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConfigurationEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(config_id, confKey, confValue);
    }

    @Override
    public String toString() {
        return "ConfigurationEntity(config_id=" + config_id + ", confKey=" + confKey
                + ", confValue=" + confValue + ")";
    }

}
