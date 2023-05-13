package com.github.szabogabriel.minidashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;

public interface DataEntryRepository extends JpaRepository<DataEntryEntity, Long> {
	
	public List<DataEntryEntity> findAllByDomain(DomainEntity domain);
	
	public Optional<DataEntryEntity> findFirstByDomainAndCategoryAndEntry(DomainEntity domain, String category, String entry);

}
