package com.amy.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amy.demo.entity.Category;

	@Repository
	public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor {

		
		
		
	}

