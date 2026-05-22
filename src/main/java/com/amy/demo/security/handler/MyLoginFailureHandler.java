package com.amy.demo.security.handler;

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyLoginFailureHandler implements AuthenticationFailureHandler {
	  @Override
	  public void onAuthenticationFailure(
	      HttpServletRequest request,
	      HttpServletResponse response,
	      AuthenticationException exception) throws IOException, ServletException {

//	  	根據失敗例外型態，指定不同錯誤訊息
	      String errorMsg = "帳號或密碼錯誤";
	      if (exception instanceof LockedException) {
	          errorMsg = "帳號已鎖定，請聯絡管理員";
	      } else if (exception instanceof DisabledException) {
	          errorMsg = "帳號已停用，請聯絡管理員";
	      } else if (exception instanceof AccountExpiredException) {
	          errorMsg = "帳號已過期，請聯絡管理員";
	      } else if (exception instanceof CredentialsExpiredException) {
	          errorMsg = "密碼已過期，請聯絡管理員";
	      } else if (exception instanceof BadCredentialsException) {
	          errorMsg = "帳號或密碼有誤";
	      }

	      // 帶錯誤訊息到登入頁
	      response.sendRedirect(request.getContextPath() + "/login/index?error=" + java.net.URLEncoder.encode(errorMsg, "UTF-8"));
	  }
}