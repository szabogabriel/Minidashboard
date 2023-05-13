package com.github.szabogabriel.minidashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;

public interface DomainRepository extends JpaRepository<DomainEntity, Long>{
	
	public Optional<DomainEntity> findDomainByName(String name);

}
