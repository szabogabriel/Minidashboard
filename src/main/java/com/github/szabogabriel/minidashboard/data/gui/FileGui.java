package com.github.szabogabriel.minidashboard.data.gui;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileGui {
	
	private Long id;
	private String fileName;
	private String mimeType;
	private LocalDateTime createTime;

}
