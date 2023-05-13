package com.github.szabogabriel.minidashboard.data.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DomainEntity {

	@Id
	@GeneratedValue
	private Long domain_id;
	
	private String name;
	
}
