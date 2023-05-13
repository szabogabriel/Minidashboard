package com.github.szabogabriel.minidashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.szabogabriel.minidashboard.data.api.DataRequest;
import com.github.szabogabriel.minidashboard.data.api.DataResponse;
import com.github.szabogabriel.minidashboard.service.ApiService;

@RestController
@RequestMapping("/api")
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
			@PathVariable(value = "category") String category, @PathVariable(value = "entry") String entry) {
		apiService.deleteEntry(domain, category, entry);
		return "";
	}
}
