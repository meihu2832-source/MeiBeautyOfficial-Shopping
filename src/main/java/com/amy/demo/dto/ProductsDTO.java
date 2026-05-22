package com.amy.demo.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
//	=====datatables 分頁排序=======
    public int draw;//代表序號，傳進來跟傳回去的數字要是一樣的，表示是同一個請求，若數字不一樣就不會接
    public int start;
    public int length;
    private List<DatatablesOrder> order;
    private List<DatatablesColumn> columns;
//    ===============================================
	private Long productId;
	private String photoUrl;
	private String name;
	private Long price;
	private Long categoryId;
	
	
	private String searchName;


	public void add(ProductsDTO dtotmp) {
		// TODO Auto-generated method stub
		
	}
	
}
