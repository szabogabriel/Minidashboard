package com.github.szabogabriel.minidashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;
import com.github.szabogabriel.minidashboard.repository.DataEntryRepository;

@Service
public class DataEntryService {

	@Autowired
	private DataEntryRepository dataEntryRepo;

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
	
	public List<DataEntryEntity> getEntries() {
		return dataEntryRepo.findAll();
	}
	
	public List<DataEntryEntity> getEntries(DomainEntity domain) {
		return dataEntryRepo.findAllByDomain(domain);
	}
	
	public List<DataEntryEntity> getEntries(DomainEntity domain, String category) {
		return dataEntryRepo.findAllByDomainAndCategory(domain, category);
	}
	
	public void deleteEntry(DomainEntity domain, String category, String entry) {
		Optional<DataEntryEntity> optionalDataEntryEntity = dataEntryRepo.findFirstByDomainAndCategoryAndEntry(domain, category, entry);
		
		if (optionalDataEntryEntity.isPresent()) {
			DataEntryEntity dee = optionalDataEntryEntity.get();
			
			dataEntryRepo.delete(dee);
		}
	}
	
	public List<DataEntryEntity> deleteCategory(DomainEntity domain, String category) {
		return dataEntryRepo.deleteByDomainAndCategory(domain, category);
	}
	
	public List<DataEntryEntity> deleteDomain(DomainEntity domain) {
		return dataEntryRepo.deleteByDomain(domain);
	}
	
}
