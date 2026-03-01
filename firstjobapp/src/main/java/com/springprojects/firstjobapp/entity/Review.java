package com.springprojects.firstjobapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private double rating;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	public Review() {
		
	}
	
	public Review(Long id, String title, String description, double rating, Company company) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.company = company;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", title=" + title + ", description=" + description + ", rating=" + rating
				+ ", company=" + company + "]";
	}
	
	
	
	

}
