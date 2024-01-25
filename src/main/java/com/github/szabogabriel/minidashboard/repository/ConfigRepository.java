package com.github.szabogabriel.minidashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.szabogabriel.minidashboard.data.entites.ConfigurationEntity;

@Repository
@Transactional
public interface ConfigRepository extends JpaRepository<ConfigurationEntity, Long>{

    public Optional<ConfigurationEntity> findByConfKey(String key);

    @Query("SELECT ce FROM ConfigurationEntity ce WHERE ce.confKey like :key")
    public List<ConfigurationEntity> findAllEntitiesViaWildcard(String key);
    
}
