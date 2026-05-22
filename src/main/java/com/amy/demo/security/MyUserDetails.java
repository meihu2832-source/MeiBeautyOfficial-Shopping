package com.amy.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.amy.demo.entity.Users;

//這段 MyUserDetails 主要是把你自訂的 Users 物件「包裝成 Spring Security 認得的帳號物件」
//實作了 Spring Security 的 UserDetails 介面
//這是 Security 認帳號資料的「標準格式」
//你只要把自己的 Users Entity 傳進來，Spring Security 就會照你寫的內容來比對
//現在是要把Spring裡面預設的方法改成自己定義的方法~(implements UserDetails)
public class MyUserDetails implements UserDetails {

  private final Users user;

  // 建構子，Spring 會自動注入進來
  public MyUserDetails(Users user) {
      this.user = user;
  }

  // 回傳這個帳號的權限列表
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
  	// 這裡預設所有帳號都給 ROLE_USER 權限（如要分權限可自己修改）
      return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  // 取得密碼（Spring Security 會拿這個跟使用者輸入的密碼比對）
  @Override
  public String getPassword() {
      return user.getPwd();
  }

  // 取得帳號（登入用的名稱，對應你的 Users Entity）
  @Override
  public String getUsername() {
      return user.getName();
  }
  
  
  // 你還可以選擇性 override 以下方法，控制帳號是否啟用、過期、鎖定等
  // 這些方法如果你 entity 有紀錄就照資料回傳，沒有可以都先回傳 true
  
  //帳號是否未過期
  @Override
  public boolean isAccountNonExpired() { 
      // 資料庫欄位 accountExpired == true 表示未過期（正常）
      return !user.isAccountExpired();
  }

  //帳號是否未鎖定
  @Override
  public boolean isAccountNonLocked() {
  	return !user.isAccountLocked(); // 假如 true=已鎖定, false=未鎖定
  }

  //密碼是否未過期
  @Override
  public boolean isCredentialsNonExpired() {
  	return !user.isCredentialsExpired(); // 假如 true=已過期, false=未過期
  }
  //如果你自訂自己的方法，當然可以用 isCredentialsExpired()，回傳「已過期」或「沒過期」的真偽，這樣比較符合直覺。
  //但如果是實作 Spring Security 的 UserDetails，就必須實作 isCredentialsNonExpired()，必須回傳「沒過期」的布林值，這時候就需要加 ! 把狀態反轉。

  //是否啟用
  @Override
  public boolean isEnabled() {
  	 return user.isEnabled(); // 一般 true=啟用, false=禁用
  }

}

