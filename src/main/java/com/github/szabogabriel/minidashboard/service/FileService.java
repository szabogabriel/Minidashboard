package com.github.szabogabriel.minidashboard.service;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.entites.FileEntity;
import com.github.szabogabriel.minidashboard.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepo;
	
	public Long uploadFile(InputStream is, String fileName, String type, long size) {
		FileEntity fileEntity = new FileEntity();
		
		fileEntity.setFileName(fileName);
		fileEntity.setCreateTimestamp(System.currentTimeMillis());
		fileEntity.setMimeType(type);
		fileEntity.setBinaryContent(BlobProxy.generateProxy(is, size));
		
		FileEntity ret = fileRepo.save(fileEntity);
		
		return ret.getFile_id();
	}
	
	public List<FileEntity> getAllEntries() {
		return fileRepo.findAll();
	}
	
	public Optional<FileEntity> getFileInputStream(Long id) {
		return fileRepo.findById(id);
	}

	public Optional<FileEntity> findById(Long id) {
		return fileRepo.findById(id);
	}

	public String getFileContentString(FileEntity fileEntity) {
		String ret = "";

		Blob blob = fileEntity.getBinaryContent();
		try (InputStream in = blob.getBinaryStream()) {
			byte[] buffer = new byte[1024];
			int read = 0;

			while ((read = in.read(buffer)) > 0) {
				ret += new String(buffer, 0, read);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	public void removeFile(Long id) {
		fileRepo.deleteById(id);
	}
	
}
