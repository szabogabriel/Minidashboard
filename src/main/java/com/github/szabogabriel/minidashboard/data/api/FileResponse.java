package com.github.szabogabriel.minidashboard.data.api;

import java.io.InputStream;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileResponse {
	
	private Long id;
	private String fileName;
	private String mimeType;
	private LocalDateTime createTime;
	private InputStream inputStream;

}
