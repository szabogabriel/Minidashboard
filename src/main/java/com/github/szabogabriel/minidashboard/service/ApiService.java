package com.github.szabogabriel.minidashboard.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.api.DataRequest;
import com.github.szabogabriel.minidashboard.data.api.DataResponse;
import com.github.szabogabriel.minidashboard.data.api.FileResponse;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;
import com.github.szabogabriel.minidashboard.data.entites.FileEntity;
import com.github.szabogabriel.minidashboard.util.DateUtils;

@Service
public class ApiService {

	@Autowired
	private DataEntryService dataEntryService;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private DomainService domainService;

	public void deleteEntry(String domainName, String category, String entry, boolean soft) {
		Optional<DomainEntity> optionalDomainEntity = domainService.getDomain(domainName);
		
		if (optionalDomainEntity.isPresent()) {
			dataEntryService.deleteEntry(optionalDomainEntity.get(), category, entry, soft);
		}
	}
	
	public void deleteCategory(String domain, String category) {
		Optional<DomainEntity> domainEntity = domainService.getDomain(domain);

		if (domainEntity.isPresent()) {
			dataEntryService.deleteCategory(domainEntity.get(), category);
		}
		
	}
	
	public void deleteDomain(String domain) {
		Optional<DomainEntity> domainEntity = domainService.getDomain(domain);

		if (domainEntity.isPresent()) {
			dataEntryService.deleteDomain(domainEntity.get());
			
			domainService.deleteDomain(domain);
		}
	}

	public void createEntry(String domainName, String category, String entry, DataRequest attributes) {
		DataEntryEntity dee = new DataEntryEntity();

		dee.setDomain(domainService.fetchOrCreateDomainEntity(domainName));

		dee.setCategory(category);

		dee.setEntry(entry);

		dee.setLevel0(attributes.getLevel0());
		dee.setLevel1(attributes.getLevel1());
		dee.setLevel2(attributes.getLevel2());
		dee.setLevel3(attributes.getLevel3());
		dee.setLevel4(attributes.getLevel4());
		dee.setLevel5(attributes.getLevel5());
		dee.setLevel6(attributes.getLevel6());
		dee.setLevel7(attributes.getLevel7());
		
		dataEntryService.createEntry(dee);
	}

	public List<DataResponse> getEntries() {
		return dataEntryService.getEntries().stream().map(this::map).collect(Collectors.toList());
	}

	public List<DataResponse> getEntries(String domain) {
		List<DataResponse> ret = Collections.emptyList();

		Optional<DomainEntity> domainEntity = domainService.getDomain(domain);

		if (domainEntity.isPresent()) {
			ret = dataEntryService.getEntries(domainEntity.get()).stream().map(this::map).collect(Collectors.toList());
		}

		return ret;
	}

	public List<DataResponse> getEntries(String domain, String category) {
		return getEntries(domain).stream().filter(e -> e.getCategory().equals(category)).collect(Collectors.toList());
	}
	
	public String createFile(InputStream is, String fileName, String mimeType, long size) {
		Long id = fileService.uploadFile(is, fileName, mimeType, size);

		return "" + id;
	}
	
	public List<FileResponse> getAllFile() {
		return fileService.getAllEntries().stream().map(this::map).collect(Collectors.toList());
	}
	
	public FileResponse getFile(Long id) {
		FileResponse ret = null;
		Optional<FileEntity> file = fileService.getFileInputStream(id);
		
		if (file.isPresent()) {
			ret = map(file.get());
			try {
				ret.setInputStream(file.get().getBinaryContent().getBinaryStream());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public void removeFile(Long id) {
		fileService.removeFile(id);
	}
	
	private FileResponse map(FileEntity fe) {
		FileResponse ret = new FileResponse();
		
		ret.setCreateTime(DateUtils.fromMillies(fe.getCreateTimestamp()));
		ret.setFileName(fe.getFileName());
		ret.setId(fe.getFile_id());
		ret.setMimeType(fe.getMimeType());
		
		return ret;
	}

	private DataResponse map(DataEntryEntity dee) {
		DataResponse ret = new DataResponse();
		ret.setDomain(dee.getDomain().getName());
		ret.setCategory(dee.getCategory());
		ret.setEntry(dee.getEntry());
		ret.setLevel0(dee.getLevel0());
		ret.setLevel1(dee.getLevel1());
		ret.setLevel2(dee.getLevel2());
		ret.setLevel3(dee.getLevel3());
		ret.setLevel4(dee.getLevel4());
		ret.setLevel5(dee.getLevel5());
		ret.setLevel6(dee.getLevel6());
		ret.setLevel7(dee.getLevel7());
		ret.setLastModified(DateUtils.fromMillies(dee.getLastModified()));
		return ret;
	}
}
