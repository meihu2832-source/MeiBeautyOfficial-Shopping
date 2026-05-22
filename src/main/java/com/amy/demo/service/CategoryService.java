package com.amy.demo.service;

import java.util.List;

import com.amy.demo.entity.Category;

public interface CategoryService {

	List<Category> getBreadcrumb(Long categoryId);

}
