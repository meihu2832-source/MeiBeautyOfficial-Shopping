package com.amy.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amy.demo.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor {
	
	Optional<Users> findByName(String name);
	
//	你必須自己在傳參數時加上 %，
//	因為 Like 方法只會把參數帶進 SQL 的 LIKE，不會自動補 %。
	List<Users> findByNameLike(String name);
}
