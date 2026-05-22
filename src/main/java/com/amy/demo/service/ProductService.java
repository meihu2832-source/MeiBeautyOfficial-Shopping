package com.amy.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.amy.demo.dto.ProductsDTO;
import com.amy.demo.entity.Products;

public interface ProductService {

	public Page<Products> doQueryProductsListByNamePage(ProductsDTO dto, Pageable pageable);

	public Long getAllCount();

	List<ProductsDTO> findProductsByIds(List<Long> ids);

	Long totalPrice(List<Long> ids);

	public  ProductsDTO findById(long id);

//	public List<ProductsDTO> findAll();

}
