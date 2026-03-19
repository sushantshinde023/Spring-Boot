package com.spring.microservicesproject.companyms.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;
	private String name;
	private String description;
	private Double rating;
	
	
	
	
	public Company() {
		
	}

	

	



	public Double getRating() {
		return rating;
	}







	public void setRating(Double rating) {
		this.rating = rating;
	}







	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	







	
	

}
