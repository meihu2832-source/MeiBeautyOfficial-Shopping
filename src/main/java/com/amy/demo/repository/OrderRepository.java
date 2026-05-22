package com.amy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amy.demo.entity.placedOrder;

@Repository
public interface OrderRepository extends JpaRepository<placedOrder, Long>, JpaSpecificationExecutor{

}
