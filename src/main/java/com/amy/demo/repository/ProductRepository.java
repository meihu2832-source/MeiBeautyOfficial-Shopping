package com.amy.demo.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.amy.demo.entity.Products;

	@Repository
	public interface ProductRepository extends JpaRepository<Products, Long>, JpaSpecificationExecutor {
		
		public Page<Products> findByName(String name, Pageable pageable);
		
//		public List<Products> findByName(String name);

		public Page<Products> findByNameContaining(String searchName, Pageable pageable);
		
		
	}

