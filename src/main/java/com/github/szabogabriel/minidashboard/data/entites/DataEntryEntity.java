package com.github.szabogabriel.minidashboard.data.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DataEntryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long data_entry_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "domain_id")
	private DomainEntity domain;
	
	private String category;
	
	private String entry;
	
	private String level0;
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	private String level6;
	private String level7;
	
	private Long createTimestamp;
	private Long lastModified;

}
