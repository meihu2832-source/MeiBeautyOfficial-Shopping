package com.amy.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "placedorder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class placedOrder {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_Id")
    private Long orderId;
	
	@Column(name = "name") 
    private String name;
	
	@Column(name = "age") 
    private Integer age;
	
	@Column(name = "birth_date") 
    private Date birthDate;
	
	@Column(name = "sex") 
    private String sex;
	
	@Column(name = "email") 
    private String email;
	
	@Column(name = "address") 
    private String address;
	
	@Column(name = "payment") 
    private String payment;
	
	@Column(name = "shipping") 
    private String shipping;
	
	@Column(name = "product_ids") 
    private String productIds;
	
    @Column(name = "create_date")
    private Date createDate;
    
    @Column(name = "update_date")
    private Date updateDate;
    
    @Column(name = "creator")
    private String creator;
    
    @Column(name = "modifier")
    private String modifier;
	

}
