package com.github.szabogabriel.minidashboard.data.enums;

public enum ConfigurationEnum {

    APPLICATION_NAME("app.name", "Dashboard"),
    MENU_FILES("menu.files", "Files"),
    BUTTON_SUBMIT("button.submit", "Submit"),
    VIEW_FILES_TITLE("view.files.title", "Available files"),
    VIEW_FILES_FILE_NAME("view.files.file.name", "File name"),
    VIEW_FILES_MIME_TYPE("view.files.mime.type", "Mime type"),
    VIEW_FILES_CREATED_AT("view.files.created.at", "Created at"),
    VIEW_FILES_VIEW_LINK("view.files.view.link", "View link"),
    VIEW_FILES_DOWNLOAD_LINK("view.files.download.link", "Download link"),
    VIEW_FILES_DELETE_LINK("view.files.delete.link", "Delete link"),
    VIEW_FILES_VIEW("view.files.view", "View"),
    VIEW_FILES_DOWNLOAD("view.files.download", "Download"),
    VIEW_FILES_DELETE("view.files.delete", "Delete"),
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
