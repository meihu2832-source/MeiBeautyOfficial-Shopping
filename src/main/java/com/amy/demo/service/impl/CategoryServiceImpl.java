package com.amy.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import com.amy.demo.entity.Category;
import com.amy.demo.repository.CategoryRepository;
import com.amy.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> getBreadcrumb(Long categoryId) {
        List<Category> breadcrumb = new ArrayList<>();

        Category current = categoryRepository.findById(categoryId).orElse(null);

        // 一直往上找
        while (current != null) {
            breadcrumb.add(current);
            if (current.getParentId() == null) break;
            current = categoryRepository.findById(current.getParentId()).orElse(null);//把 current 換成「上一層」
        }

        // 反轉：因為我們是從最下面往上找
        Collections.reverse(breadcrumb);

        return breadcrumb;
    }

}
