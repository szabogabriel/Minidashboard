package com.github.szabogabriel.minidashboard.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.szabogabriel.minidashboard.data.api.DataRequest;
import com.github.szabogabriel.minidashboard.data.api.DataResponse;
import com.github.szabogabriel.minidashboard.data.api.FileResponse;
import com.github.szabogabriel.minidashboard.service.ApiService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api("Dashboard API controller for managing the content.")
public class ApiController {

	@Autowired
	private ApiService apiService;

	@PostMapping("/data/{domain}/{category}/{entry}")
	public String createNewData(@PathVariable(value = "domain") String domain,
			@PathVariable(value = "category") String category, @PathVariable(value = "entry") String entry,
			@RequestBody DataRequest data) {
		apiService.createEntry(domain, category, entry, data);
		return "";
	}

	@PostMapping("/data/{domain}/{category}/{entry}/{level0}")
	public String createNewDataLevel0(@PathVariable(value = "domain") String domain,
			@PathVariable(value = "category") String category, @PathVariable(value = "entry") String entry,
			@PathVariable(value = "level0") String level0) {
		DataRequest dataRequest = new DataRequest();
		dataRequest.setLevel0(level0);
		apiService.createEntry(domain, category, entry, dataRequest);
		return "";
	}

	@PostMapping("/data/{domain}/{category}/{entry}/{level0}/{level1}")
	public String createNewDataLevel0(@PathVariable(value = "domain") String domain,
			@PathVariable(value = "category") String category, @PathVariable(value = "entry") String entry,
			@PathVariable(value = "level0") String level0, @PathVariable(value = "level1") String level1) {
		DataRequest dataRequest = new DataRequest();
		dataRequest.setLevel0(level0);
		dataRequest.setLevel1(level1);
		apiService.createEntry(domain, category, entry, dataRequest);
		return "";
	}

	@PostMapping("/data/{domain}/{category}/{entry}/{level0}/{level1}/{level2}")
	public String createNewDataLevel0(@PathVariable(value = "domain") String domain,
			@PathVariable(value = "category") String category, @PathVariable(value = "entry") String entry,
			@PathVariable(value = "level0") String level0, @PathVariable(value = "level1") String level1,
			@PathVariable(value = "level2") String level2) {
		DataRequest dataRequest = new DataRequest();
		dataRequest.setLevel0(level0);
		dataRequest.setLevel1(level1);
		dataRequest.setLevel2(level2);
		apiService.createEntry(domain, category, entry, dataRequest);
		return "";
	}

	@PostMapping("/file")
	public String uploadFile(@RequestParam MultipartFile file) {
		String ret = "";
		try {
			String fileName = file.getOriginalFilename();
			InputStream in = file.getInputStream();
			String mimeType = file.getContentType();
			Long size = file.getSize();
			
			ret = apiService.createFile(in, fileName, mimeType, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/file")
	public List<FileResponse> getAllFiles() {
		return apiService.getAllFile();
	}
	
	@GetMapping("/file/{fileId}")
	public ResponseEntity<Resource> getFileContent(@PathVariable(value = "fileId") String fileId) {
		FileResponse fr = apiService.getFile(Long.parseLong(fileId));
		
		Resource res = new InputStreamResource(fr.getInputStream());
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", fr.getFileName());
		
        return ResponseEntity.ok()
                .headers(headers)
//                .contentLength(fileBytes.length) //TODO
                .body(res);
	}
	
	@GetMapping("/data")
	public List<DataResponse> dataEntries() {
		return apiService.getEntries();
	}

	@GetMapping("/data/{domain}")
	public List<DataResponse> getDomainData(@PathVariable(value = "domain") String domain) {
		return apiService.getEntries(domain);
	}

	@GetMapping("/data/{domain}/{category}")
	public List<DataResponse> getDomainData(@PathVariable(value = "domain") String domain,
			@PathVariable(value = "category") String category) {
		return apiService.getEntries(domain);
	}

	@DeleteMapping("/data/{domain}/{category}/{entry}")
	public String deleteData(@PathVariable(value = "domain") String domain,
			@PathVariable(value = "category") String category, @PathVariable(value = "entry") String entry,
			@RequestParam(defaultValue = "true") String softDelete) {
		apiService.deleteEntry(domain, category, entry, Boolean.parseBoolean(softDelete));
		return "";
	}

	@DeleteMapping("/data/{domain}/{category}")
	public String deleteCategory(@PathVariable(value = "domain") String domain,
			@PathVariable(value = "category") String category) {
		apiService.deleteCategory(domain, category);
		return "";
	}

	@DeleteMapping("/data/{domain}")
	public String deleteCategory(@PathVariable(value = "domain") String domain) {
		apiService.deleteDomain(domain);
		return "";
	}
	
	@DeleteMapping("/file/{fileId}")
	public void deleteFileContent(@PathVariable(value = "fileId") String fileId) {
		if (fileId != null) {
			try {
				Long fileIdLong = Long.parseLong(fileId);
				apiService.removeFile(fileIdLong);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
