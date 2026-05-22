package com.amy.demo.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Service;

import com.amy.demo.dto.OrderFormDTO;
import com.amy.demo.entity.placedOrder;
import com.amy.demo.repository.OrderRepository;
import com.amy.demo.service.OrderPlacingService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderPlacingServiceImpl implements OrderPlacingService{
	private final OrderRepository orderRepository;
	
	@Override
	@Transactional
	public boolean doCreateOrder(OrderFormDTO orderDTO){
		placedOrder add01 = new placedOrder();
		add01.setName(orderDTO.getName());
		if(orderDTO.getAge() != null && !"".equals(orderDTO.getAge())) {
			add01.setAge(Integer.valueOf(orderDTO.getAge()));
		}
		
		if(orderDTO.getBirthDate() != null && !"".equals(orderDTO.getBirthDate())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 Date date = new Date();
			try {
				date = formatter.parse(orderDTO.getBirthDate());
				add01.setBirthDate(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		add01.setSex(orderDTO.getSex());
		add01.setEmail(orderDTO.getEmail());
		add01.setAddress(orderDTO.getAddress());
		add01.setPayment(orderDTO.getPayment());
		add01.setShipping(orderDTO.getShipping());
		add01.setProductIds(orderDTO.getProductIds_bought());
		add01.setCreateDate(new Date());
		add01.setUpdateDate(new Date());
		add01.setCreator(orderDTO.getName());
		add01.setModifier(orderDTO.getName());
		
		try {
		    orderRepository.save(add01);
		    return true;
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}
		
	}
}
