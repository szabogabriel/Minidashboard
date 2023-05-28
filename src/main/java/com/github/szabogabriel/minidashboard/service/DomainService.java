package com.github.szabogabriel.minidashboard.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;
import com.github.szabogabriel.minidashboard.repository.DomainRepository;

@Service
public class DomainService {

	@Autowired
	private DomainRepository domainRepo;
	
	public DomainEntity fetchOrCreateDomainEntity(String name) {
		Optional<DomainEntity> domainEntity = domainRepo.findDomainByName(name);

		if (domainEntity.isEmpty()) {
			DomainEntity de = new DomainEntity();
			de.setName(name);
			domainRepo.save(de);
			
			domainEntity = Optional.of(de);
		}
		
		return domainEntity.get();
	}
	
	public List<String> getDomainNames() {
		return domainRepo.findAll().stream().map(e -> e.getName()).collect(Collectors.toList());
	}
	
	public Optional<DomainEntity> getDomain(String name) {
		return domainRepo.findDomainByName(name);
	}
	
	public void deleteDomain(String name) {
		domainRepo.deleteDomainByName(name);
	}
	
}
