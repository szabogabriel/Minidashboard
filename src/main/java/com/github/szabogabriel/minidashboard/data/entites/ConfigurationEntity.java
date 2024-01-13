package com.github.szabogabriel.minidashboard.data.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long config_id;

    private String confKey;

    private String confValue;
    
}
