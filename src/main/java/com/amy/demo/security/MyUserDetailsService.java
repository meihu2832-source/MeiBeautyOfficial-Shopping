package com.amy.demo.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amy.demo.entity.Users;
import com.amy.demo.repository.UsersRepository;

//這是 Spring Security 的自訂帳號查詢（UserDetailsService），
//用來讓 Spring Security 驗證登入時，從你自己的 Users 資料表查詢帳號資料。
@Service
//你的這段 MyUserDetailsService 是 Spring Security 內部登入流程 自動會呼叫的，不需要你自己去寫 Controller 來觸發。
//UserDetailsService：Spring Security 內建的帳號查詢介面
public class MyUserDetailsService implements UserDetailsService {

	//我們自己自定義的UsersRepository
  private final UsersRepository usersRepository;

  // 建構子注入，Spring 會自動注入進來
  public MyUserDetailsService(UsersRepository userRepository) {
      this.usersRepository = userRepository;
  }

  // 自己實作 UserDetailsService 介面的方法
  // 這是 Spring Security 會呼叫來查詢用戶資料的方法
  @Override
  public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  	
  	// 用你自訂的 repository 查詢帳號
  	Users user = usersRepository.findByName(username)
              .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
  	
  	// 查到帳號後，轉成 MyUserDetails 物件（MyUserDetails那邊要實作 UserDetails 介面）
      return new MyUserDetails(user);
  }
}

