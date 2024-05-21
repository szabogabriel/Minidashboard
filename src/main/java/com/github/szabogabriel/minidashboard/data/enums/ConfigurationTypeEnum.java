package com.github.szabogabriel.minidashboard.data.enums;

public enum ConfigurationTypeEnum {

    DEFAULT(""),
    TABLE_HEADER("header:"),
    COLUMN_HANDLER("callback:"),
    REPLICATION_TARGET("replication:"),
    ;

    private String configCategory;

    private ConfigurationTypeEnum(String category) {
        this.configCategory = category;
    }
    
    public String getCategory() {
        return configCategory;
    }
    
}
