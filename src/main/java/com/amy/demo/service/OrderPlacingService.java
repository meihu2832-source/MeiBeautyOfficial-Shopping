package com.amy.demo.service;

import java.util.List;

import com.amy.demo.dto.OrderFormDTO;
import com.amy.demo.entity.placedOrder;

public interface OrderPlacingService {

	boolean doCreateOrder(OrderFormDTO orderDTO);



}
