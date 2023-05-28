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
		Optional<DataEntryEntity> tmp = dataEntryRepo.findFirstByDomainAndCategoryAndEntry(entity.getDomain(), entity.getCategory(), entity.getEntry());
		if (tmp.isEmpty()) {
			entity.setCreateTimestamp(System.currentTimeMillis());
			entity.setLastModified(entity.getCreateTimestamp());
			dataEntryRepo.save(entity);
		} else {
			DataEntryEntity dee = tmp.get();
			dee.setLevel0(entity.getLevel0());
			dee.setLevel1(entity.getLevel1());
			dee.setLevel2(entity.getLevel2());
			dee.setLevel3(entity.getLevel3());
			dee.setLevel4(entity.getLevel4());
			dee.setLevel5(entity.getLevel5());
			dee.setLevel6(entity.getLevel6());
			dee.setLevel7(entity.getLevel7());
			dee.setLastModified(System.currentTimeMillis());
			dataEntryRepo.save(dee);
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
