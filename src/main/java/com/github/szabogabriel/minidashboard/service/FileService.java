package com.github.szabogabriel.minidashboard.service;

import java.io.InputStream;
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
	
	public void removeFile(Long id) {
		fileRepo.deleteById(id);
	}
	
}
