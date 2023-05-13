package com.github.szabogabriel.minidashboard.data.api;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DataResponse {
	
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
	private LocalDateTime lastModified;

}
