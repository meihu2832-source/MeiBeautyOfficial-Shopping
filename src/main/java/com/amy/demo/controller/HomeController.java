package com.amy.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amy.demo.dto.OrderFormDTO;
import com.amy.demo.dto.ProductsDTO;
import com.amy.demo.entity.Category;
import com.amy.demo.entity.Products;
import com.amy.demo.service.CategoryService;
import com.amy.demo.service.OrderPlacingService;
import com.amy.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
	private final ProductService productService;
	private final OrderPlacingService orderPlacingService;
	private final CategoryService categoryService;
	
	//寫一個model.addAttribute 把SQL查到的產品資料丟給首頁前端 讓Thymeleaf用
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("product1", productService.findById(9L));
		System.out.println("product 1 OK");
        model.addAttribute("product2", productService.findById(10L));
        System.out.println("product 2 OK");
        model.addAttribute("product3", productService.findById(19L));
        System.out.println("product 3 OK");
        model.addAttribute("product4", productService.findById(9L));
        System.out.println("product 4 OK");
        model.addAttribute("product5", productService.findById(3L));
        System.out.println("product 5 OK");
        model.addAttribute("product6", productService.findById(16L));
        System.out.println("product 6 OK");
        model.addAttribute("product7", productService.findById(5L));
        System.out.println("product 7 OK");
        model.addAttribute("product8", productService.findById(3L));
        System.out.println("product 8 OK");
		return "index";
	}
	
	@GetMapping("/goAbout")
	public String goAbout() {
		return "about";
	}

	@GetMapping("/goContact")
	public String goContact() {
		return "contact";
	}
	
	@GetMapping("/goProducts")
	public String goProducts(@RequestParam(name="category", required=false) Long categoryId, Model model) {
		 if(categoryId != null) {
		        // 只取得 breadcrumb
		        List<Category> breadcrumb = categoryService.getBreadcrumb(categoryId);
		        model.addAttribute("breadcrumb", breadcrumb);
		    }
		return "products";
	}
	
	@GetMapping("/goSingleProduct")
	public String goSingleProduct() {
		return "single-product";
	}
	
	
	@GetMapping("/mycart")
	public String mycart() {
		return "mycart";
	}
	@GetMapping("/CheckOut")
	public String CheckOut() {
		return "CheckOut";
	}
	
    @PostMapping("/addSubmit")
    public String addSubmit(@ModelAttribute OrderFormDTO form, Model model) {
        
    	System.out.println("訂單資訊 ： " + form);
    	boolean orderAdd = orderPlacingService.doCreateOrder(form);
    	if (orderAdd) {
            model.addAttribute("message", "訂單建立成功！");
            return "AfterCheckOutSuccess";  // 轉到成功頁面
        } else {
            model.addAttribute("message", "訂單建立失敗，請稍後再試。");
            return "AfterCheckOutFail";  // 轉到失敗頁面
        }

    }
    
    @GetMapping("/heartList")
	public String heartList() {
		return "heartList";
	}
    


}
