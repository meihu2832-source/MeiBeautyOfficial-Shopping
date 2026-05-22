package com.amy.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.amy.demo.dto.ProductsDTO;
import com.amy.demo.entity.Products;
import com.amy.demo.repository.ProductRepository;
import com.amy.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	
	@Override
	public Page<Products> doQueryProductsListByNamePage(ProductsDTO dto,
			Pageable pageable) {
		
		Page<Products>list=null;
		
		if(dto !=null && dto.getSearchName()!=null && !"".equals(dto.getSearchName().trim())) {
//			trim功能：移除字串前後的空白字元（whitespace）。
//			空白字元包含：空格 ( )、tab (\t)、換行 (\n)、回車 (\r)。
//			但注意：它只會處理「前後」的空白，不會處理字串中間的空白。
			list=productRepository.findByNameContaining(dto.getSearchName(), pageable);
		}else {
			list=productRepository.findAll(pageable);//findAll方法本身可以接pageable物件，會自動回給你一個分頁
		}
		return list;
	}
	
	@Override
	public Long getAllCount() {
		// TODO Auto-generated method stub
		return productRepository.count();
	}
	
	@Override
	public List<ProductsDTO> findProductsByIds(List<Long> ids) {
		//針對前端傳回來的商品id，執行SQL的doQuery，並回傳JSON
		
			List<Products> products=productRepository.findAllById(ids);
			System.out.println(ids);
			List<ProductsDTO> listDto=new ArrayList<ProductsDTO>();		
		if(ids!=null && ids.size()>0) {

			for(Products p : products) {
				// Entity 轉 DTO
				ProductsDTO dtotmp = new ProductsDTO();		        
//					        springframework.beans.BeanUtils
					        BeanUtils.copyProperties(p, dtotmp);
					        listDto.add(dtotmp);
			}
			
		}
		return listDto;
	}
	
	
	@Override
	public Long totalPrice(List<Long> ids) {
		System.out.println("商品清單"+ids);
		//若陣列為空，回傳總金額為0
	    if (ids == null || ids.isEmpty()) {
	        return 0L;
	    }

		Long totalPrice = 0L;

		 // 對照前端傳回來的 id 陣列
	    for (Long id : ids) {
	    	//每個陣列內容都重新執行findAllById，重複的id才能都跑到
	    	Products products = productRepository.findById(id)
	    	        .orElseThrow(() -> new RuntimeException("商品不存在，id=" + id));
	    	ProductsDTO dtotmp = new ProductsDTO();	
	    	BeanUtils.copyProperties(products, dtotmp);

	    	// 用 DTO 計算
	        totalPrice += dtotmp.getPrice();
	    }

	    return totalPrice;

}

	@Override
	public ProductsDTO findById(long id) {
		Products product=productRepository.findById(id).orElseThrow(() -> new RuntimeException("找不到產品ID : " + id));

			// Entity 轉 DTO
			ProductsDTO dtotmp = new ProductsDTO();		        
//				        springframework.beans.BeanUtils
				        BeanUtils.copyProperties(product, dtotmp);		
		
		return dtotmp;
	}

//	@Override
//	public List<ProductsDTO> findAll() {
//		
//		List<ProductsDTO> listDto=new ArrayList<ProductsDTO>();
//		
//		for(Products p : products) {
//			// Entity 轉 DTO
//			ProductsDTO dtotmp = new ProductsDTO();		        
////				        springframework.beans.BeanUtils
//				        BeanUtils.copyProperties(p, dtotmp);
//				        listDto.add(dtotmp);
//		}
//		
//		return listDto;
//	}
	
	
	
	}
