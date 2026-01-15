package com.github.szabogabriel.minidashboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import com.github.szabogabriel.minidashboard.data.dto.DataEntryDto;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;
import com.github.szabogabriel.minidashboard.repository.DataEntryRepository;

@Service
public class DataEntryService {

	@Autowired
	private DataEntryRepository dataEntryRepo;

	@Autowired
	private EntryHandlerService entryHandlerService;

	public void createEntry(DataEntryEntity entity) {
		List<DataEntryEntity> tmp = dataEntryRepo.findAllByDomainAndCategoryAndEntry(entity.getDomain(), entity.getCategory(), entity.getEntry());
		entity.setCreateTimestamp(System.currentTimeMillis());
		entity.setLastModified(entity.getCreateTimestamp());
		
		if (tmp.isEmpty()) {
			entity.setVersion(1L);
			dataEntryRepo.save(entity);
		} else {
			DataEntryEntity dee = tmp.stream().sorted((e1, e2) -> e2.getVersion().compareTo(e1.getVersion())).findFirst().orElse(null);
			dee.setValidUntil(entity.getCreateTimestamp());
			dee.setLastModified(entity.getCreateTimestamp());
			
			Long oldVersion = dee.getVersion();
			if (oldVersion == null) {
				oldVersion = 1L;
				dee.setVersion(oldVersion);
			}
			entity.setVersion(oldVersion + 1);
			
			dataEntryRepo.save(dee);
			dataEntryRepo.save(entity);
		}
	}
	
	public List<DataEntryDto> getEntries() {
		return handleViaEntryHandler(dataEntryRepo.findAll());
	}
	
	public List<DataEntryDto> getEntries(DomainEntity domain) {
		return handleViaEntryHandler(dataEntryRepo.findAllByDomain(domain));
	}
	
	public List<DataEntryDto> getCurrentEntries(DomainEntity domain) {
		return handleViaEntryHandler(dataEntryRepo.findAllByDomainAndValidUntil(domain, null));
	}
	
	public List<DataEntryDto> getEntries(DomainEntity domain, String category) {
		return handleViaEntryHandler(dataEntryRepo.findAllByDomainAndCategory(domain, category));
	}
	
	public List<DataEntryDto> getEntries(DomainEntity domain, String category, String entry) {
		return handleViaEntryHandler(dataEntryRepo.findAllByDomainAndCategoryAndEntry(domain, category, entry));
	}

	public List<DataEntryDto> getEntriesPaged(DomainEntity domain, String category, String entry, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createTimestamp").descending());
		return handleViaEntryHandler(dataEntryRepo.findAllByDomainAndCategoryAndEntry(pageable, domain, category, entry));
	}

	public int getCountOfEntries(DomainEntity domain, String category, String entry) {
		return dataEntryRepo.countByDomainAndCategoryAndEntry(domain, category, entry);
	}


	private List<DataEntryDto> handleViaEntryHandler(List<DataEntryEntity> entities) {
		List<DataEntryDto> ret = new ArrayList<>(entities.size());
		for (DataEntryEntity it : entities) {
			ret.add(entryHandlerService.handleDataEntry(it));
		}
		return ret;
	}
	
	public void deleteEntry(DomainEntity domain, String category, String entry, boolean soft) {
		Optional<DataEntryEntity> optionalDataEntryEntity = dataEntryRepo.findFirstByDomainAndCategoryAndEntry(domain, category, entry);
		
		if (optionalDataEntryEntity.isPresent()) {
			DataEntryEntity dee = optionalDataEntryEntity.get();

			if (soft) {
				Long timestamp = System.currentTimeMillis();
				dee.setLastModified(timestamp);
				dee.setValidUntil(timestamp);
				
				dataEntryRepo.save(dee);
			} else {
				dataEntryRepo.delete(dee);
			}
		}
	}
	
	public List<DataEntryEntity> deleteCategory(DomainEntity domain, String category) {
		return dataEntryRepo.deleteByDomainAndCategory(domain, category);
	}
	
	public List<DataEntryEntity> deleteDomain(DomainEntity domain) {
		return dataEntryRepo.deleteByDomain(domain);
	}
	
}
