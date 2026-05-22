package com.amy.demo.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFormDTO {

    // 基本資料

    private String name;
    private Integer age;
    private String birthDate;
    private String sex; // M / F / O
    private String email;
    private String address;

    // 付款方式
    private String payment; // 值可能是 "creditCard", "linePay", "cash"

    // 配送方式
    private String shipping; // 值可能是 "shipHome", "conPickupCash", "conPickupNoCash"

    //隱藏欄位
    private String productIds_bought;
    


}