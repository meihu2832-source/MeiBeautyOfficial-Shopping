package com.amy.demo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amy.demo.dto.UsersDTO;
import com.amy.demo.entity.Users;
import com.amy.demo.repository.UsersRepository;
import com.amy.demo.service.UsersService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	
  	@Override
	@Transactional
	public int doCreate(UsersDTO dto){
  		try {
		Users users = new Users();
		users.setName(dto.getName());
		//因為我們希望密碼是加密過的 所以要注入Spring的 passwordEncoder 用他的加密方法
		users.setPwd(passwordEncoder.encode(dto.getPwd()));
		users.setAccountExpired(false);
		users.setAccountLocked(false);
		users.setCredentialsExpired(false);
		users.setEnabled(true);
		
		usersRepository.save(users);
		
		return 1;
	}catch (Exception e) {
		e.printStackTrace();
		return 0;
	}
		
	}

}
