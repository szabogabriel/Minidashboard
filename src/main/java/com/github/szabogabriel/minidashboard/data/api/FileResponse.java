package com.github.szabogabriel.minidashboard.data.api;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Objects;

public class FileResponse {
	
	private Long id;
	private String fileName;
	private String mimeType;
	private LocalDateTime createTime;
	private InputStream inputStream;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }
	public String getMimeType() { return mimeType; }
	public void setMimeType(String mimeType) { this.mimeType = mimeType; }
	public LocalDateTime getCreateTime() { return createTime; }
	public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
	public InputStream getInputStream() { return inputStream; }
	public void setInputStream(InputStream inputStream) { this.inputStream = inputStream; }

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof FileResponse)) return false;
		FileResponse that = (FileResponse) other;
		return that.canEqual(this)
				&& Objects.equals(id, that.id)
				&& Objects.equals(fileName, that.fileName)
				&& Objects.equals(mimeType, that.mimeType)
				&& Objects.equals(createTime, that.createTime)
				&& Objects.equals(inputStream, that.inputStream);
	}

	protected boolean canEqual(Object other) {
		return other instanceof FileResponse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fileName, mimeType, createTime, inputStream);
	}

	@Override
	public String toString() {
		return "FileResponse(id=" + id + ", fileName=" + fileName + ", mimeType=" + mimeType
				+ ", createTime=" + createTime + ", inputStream=" + inputStream + ")";
	}
}
