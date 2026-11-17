package com.github.szabogabriel.minidashboard.data.entites;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.util.Objects;

@Entity
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long file_id;

	private Long createTimestamp;
	
	private String fileName;
	
	private String mimeType;
	
	@Lob
	private Blob binaryContent;
	
	public Long getFile_id() { return file_id; }
	public void setFile_id(Long file_id) { this.file_id = file_id; }
	public Long getCreateTimestamp() { return createTimestamp; }
	public void setCreateTimestamp(Long createTimestamp) { this.createTimestamp = createTimestamp; }
	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }
	public String getMimeType() { return mimeType; }
	public void setMimeType(String mimeType) { this.mimeType = mimeType; }
	public Blob getBinaryContent() { return binaryContent; }
	public void setBinaryContent(Blob binaryContent) { this.binaryContent = binaryContent; }

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof FileEntity)) return false;
		FileEntity that = (FileEntity) other;
		return that.canEqual(this)
				&& Objects.equals(file_id, that.file_id)
				&& Objects.equals(createTimestamp, that.createTimestamp)
				&& Objects.equals(fileName, that.fileName)
				&& Objects.equals(mimeType, that.mimeType)
				&& Objects.equals(binaryContent, that.binaryContent);
	}

	protected boolean canEqual(Object other) {
		return other instanceof FileEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file_id, createTimestamp, fileName, mimeType, binaryContent);
	}

	@Override
	public String toString() {
		return "FileEntity(file_id=" + file_id + ", createTimestamp=" + createTimestamp
				+ ", fileName=" + fileName + ", mimeType=" + mimeType + ", binaryContent=" + binaryContent + ")";
	}

}
