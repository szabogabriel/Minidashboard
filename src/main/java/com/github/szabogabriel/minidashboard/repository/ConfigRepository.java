package com.github.szabogabriel.minidashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.szabogabriel.minidashboard.data.entites.ConfigurationEntity;

@Repository
@Transactional
public interface ConfigRepository extends JpaRepository<ConfigurationEntity, Long>{

    public Optional<ConfigurationEntity> findByConfKey(String key);
    
}
