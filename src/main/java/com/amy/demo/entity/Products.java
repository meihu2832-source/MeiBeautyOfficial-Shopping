package com.amy.demo.entity;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "productlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_Id")
    private Long productId;
	
    @Column(name = "name") 
    private String name;

    @Column(name = "photo_url") 
    private String photoUrl;
    
    @Column(name = "price")
    private Long price;
    
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "create_date")
    private Date createDate;
    
    @Column(name = "update_date")
    private Date updateDate;
    
    @Column(name = "creator")
    private String creator;
    
    @Column(name = "modifier")
    private String modifier;
    
    


}
