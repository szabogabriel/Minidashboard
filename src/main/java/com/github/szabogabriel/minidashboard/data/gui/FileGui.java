package com.github.szabogabriel.minidashboard.data.gui;

import java.util.Objects;

public class FileGui {
	
	private Long id;
	private String fileName;
	private String mimeType;
	private String createTime;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }
	public String getMimeType() { return mimeType; }
	public void setMimeType(String mimeType) { this.mimeType = mimeType; }
	public String getCreateTime() { return createTime; }
	public void setCreateTime(String createTime) { this.createTime = createTime; }

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof FileGui)) return false;
		FileGui that = (FileGui) other;
		return that.canEqual(this)
				&& Objects.equals(id, that.id)
				&& Objects.equals(fileName, that.fileName)
				&& Objects.equals(mimeType, that.mimeType)
				&& Objects.equals(createTime, that.createTime);
	}

	protected boolean canEqual(Object other) {
		return other instanceof FileGui;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fileName, mimeType, createTime);
	}

	@Override
	public String toString() {
		return "FileGui(id=" + id + ", fileName=" + fileName + ", mimeType=" + mimeType
				+ ", createTime=" + createTime + ")";
	}
}
