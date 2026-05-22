package com.amy.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amy.demo.dto.DatatablesOrder;
import com.amy.demo.dto.OrderFormDTO;
import com.amy.demo.dto.ProductsDTO;
import com.amy.demo.entity.Category;
import com.amy.demo.entity.Products;
import com.amy.demo.service.CategoryService;
import com.amy.demo.service.OrderPlacingService;
import com.amy.demo.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restController")
@RequiredArgsConstructor
public class ApiRestController {
	
	private final ProductService productService;
	private final OrderPlacingService orderPlacingService;
	private final CategoryService categoryService;

	
	@PostMapping("/dopage")
	@ResponseBody
	public Map<String, Object> dopage(@RequestBody ProductsDTO dto) {
		int draw = dto.getDraw(); //DataTables 頁面請求的序號確保回應能對應到送出的請求
		int start = dto.getStart(); //取得當前分頁的第幾筆開始
		int length = dto.getLength(); //取得每頁的筆數

		int page = start / length; //計算目前是第幾頁
		
	    // 解析排序
	    Sort sort = Sort.unsorted(); //初始化排序物件，預設為無排序
	    if (dto.getOrder() != null && !dto.getOrder().isEmpty()) {
	    	//取出排序設定，DataTables 支援多欄排序，這邊只取第一個欄位，即對PK做排序
	    	DatatablesOrder orderObj = dto.getOrder().get(0);
	    	//取得排序的欄位索引
	        int colIndex = orderObj.getColumn();//getColumn() 取得的不是欄位名字，而是 欄位在表格中的位置（索引），也就是第幾欄。
	        //取得排序升冪或降冪
	        String dir = orderObj.getDir();
	        //根據欄位索引取得對應欄位名稱，用於後端排序欄位
	        String orderColumn = dto.getColumns().get(colIndex).getData();
	        
//	        若使用者在前端點「姓名」欄排序
//	        DataTables 只會告訴你 第幾欄要排序，例如 colIndex = 1（第二欄）
//	        這時候你需要找對應的 後端欄位名稱，也就是 full_name，才能告訴資料庫排序
//	        dto.getColumns().get(colIndex).getData() 就做了這件事：
//	        dto.getColumns() → 前端欄位列表
//	        .get(colIndex) → 找到使用者點的欄位
//	        .getData() → 取得對應資料庫的欄位名稱

	        //三元運算 用來判斷排序參數 dir 是否等於 asc（不分大小寫）
	        sort = Sort.by(dir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orderColumn);
	    }
		//Page分頁結果     Pageable分頁需求
		Pageable pageable = PageRequest.of(page, length);
		
		Page<Products> pageResult = productService.doQueryProductsListByNamePage(dto, pageable);

		Map<String, Object> response = new HashMap<>();
		response.put("draw", draw);
		response.put("recordsTotal", productService.getAllCount()); //資料庫中符合條件的總筆數
		response.put("recordsFiltered", pageResult.getTotalElements()); //使用者輸入的搜尋條件或過濾條件後，符合條件的資料筆數
		response.put("data", pageResult.getContent()); //當前頁的資料
					//上面這幾個key都要這樣寫，這樣回傳給datatable他才認得出來
		
		return response;
	}
	

	
	@PostMapping("/cart/receiveCart")
	public List<ProductsDTO> receiveCart(@RequestBody List<Long> productIds) {
		System.out.println(productIds);
		List<ProductsDTO> fullProductsInfo=productService.findProductsByIds(productIds);
		return fullProductsInfo;
	}
	
	@PostMapping("/getHeart")
	public List<ProductsDTO> geatHeart(@RequestBody List<Long> productIds) {
		System.out.println(productIds);
		List<ProductsDTO> fullProductsInfoHeart=productService.findProductsByIds(productIds);
		return fullProductsInfoHeart;
	}
	
    @PostMapping("/cart/total")
    public Long calculateTotal(@RequestBody List<Long> ids) {
        return productService.totalPrice(ids);
    }
    
    @GetMapping("/breadcrumb/{id}")
    public List<Category> getBreadcrumb(@PathVariable Long id){
    	return categoryService.getBreadcrumb(id);
    }

}
