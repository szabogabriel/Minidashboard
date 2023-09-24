package com.github.szabogabriel.minidashboard.data.entites;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long file_id;

	private Long createTimestamp;
	
	private String fileName;
	
	private String mimeType;
	
	@Lob
	private Blob binaryContent;
	
}
