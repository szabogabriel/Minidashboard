package com.github.szabogabriel.minidashboard.data.enums;

public enum ConfigurationEnum {

    APPLICATION_NAME("app.name", "Dashboard"),
    BANNER("banner", "banner.png"),
    MENU_FILES("menu.files", "Files"),
    BUTTON_ADD("button.add", "Add"),
    BUTTON_SUBMIT("button.submit", "Submit"),
    STRING_CREATED("string.created", "Created"),
    STRING_LAST_CHANGED("string.lastChanged", "Last changed"),
    STRING_VALID_UNTIL("string.validUntil", "Valid Until"),
    FORMAT_DATE_GUI("format.date.gui", "yyyy-MM-dd HH:mm:ss"),
    VIEW_DATA_HISTORY("view.data.history", "History"),
    VIEW_FILES_TITLE("view.files.title", "Available files"),
    VIEW_FILES_FILE_NAME("view.files.file.name", "File name"),
    VIEW_FILES_MIME_TYPE("view.files.mime.type", "Mime type"),
    VIEW_FILES_CREATED_AT("view.files.created.at", "Created at"),
    VIEW_FILES_VIEW("view.files.view", "View"),
    VIEW_FILES_DOWNLOAD("view.files.download", "Download"),
    VIEW_FILES_DELETE("view.files.delete", "Delete"),
    NUMBER_OF_HISTORY_ITEMS("number.of.history.items", "100")
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
