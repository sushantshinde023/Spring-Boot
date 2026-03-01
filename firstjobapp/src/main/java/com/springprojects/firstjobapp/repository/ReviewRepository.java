package com.springprojects.firstjobapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.firstjobapp.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByCompanyCompanyId(Long companyId);//the method name should be like Review.company. -> as we have named primary key in company Entity as companyId we will have to name like this
	//if primary key in company entity is just id then we could use findByCompanyId

}
