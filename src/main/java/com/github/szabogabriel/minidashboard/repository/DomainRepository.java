package com.github.szabogabriel.minidashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;

@Repository
@Transactional
public interface DomainRepository extends JpaRepository<DomainEntity, Long>{
	
	public Optional<DomainEntity> findDomainByName(String name);
	
	public Optional<DomainEntity> deleteDomainByName(String name);

}
