package com.github.szabogabriel.minidashboard.data.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class DomainEntity {

	@Id
	@GeneratedValue
	private Long domain_id;
	
	private String name;
	
	public Long getDomain_id() { return domain_id; }
	public void setDomain_id(Long domain_id) { this.domain_id = domain_id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof DomainEntity)) return false;
		DomainEntity that = (DomainEntity) other;
		return that.canEqual(this)
				&& Objects.equals(domain_id, that.domain_id)
				&& Objects.equals(name, that.name);
	}

	protected boolean canEqual(Object other) {
		return other instanceof DomainEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(domain_id, name);
	}

	@Override
	public String toString() {
		return "DomainEntity(domain_id=" + domain_id + ", name=" + name + ")";
	}

}
