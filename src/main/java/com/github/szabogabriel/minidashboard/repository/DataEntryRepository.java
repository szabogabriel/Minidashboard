package com.github.szabogabriel.minidashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;

@Repository
@Transactional
public interface DataEntryRepository extends JpaRepository<DataEntryEntity, Long> {
	
	public List<DataEntryEntity> findAllByDomain(DomainEntity domain);
	
	public List<DataEntryEntity> findAllByDomainAndValidUntil(DomainEntity domain, Long validUntil);
	
	public List<DataEntryEntity> findAllByDomainAndCategory(DomainEntity domain, String category);
	
	public List<DataEntryEntity> findAllByDomainAndCategoryAndEntry(DomainEntity domain, String category, String entry);
	
	public Optional<DataEntryEntity> findFirstByDomainAndCategoryAndEntry(DomainEntity domain, String category, String entry);
	
	public List<DataEntryEntity> deleteByDomainAndCategory(DomainEntity domain, String category);
	
	public List<DataEntryEntity> deleteByDomain(DomainEntity domain);

}
