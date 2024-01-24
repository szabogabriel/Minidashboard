package com.github.szabogabriel.minidashboard.data.dto;

import lombok.Data;

@Data
public class DataEntryDto {
    private String domain;

    private String category;

    private String entry;

    private String level0;
    private String level1;
    private String level2;
    private String level3;
    private String level4;
    private String level5;
    private String level6;
    private String level7;

    private Long createTimestamp;
    private Long lastModified;
    private Long validUntil;

    private Long version;
}
