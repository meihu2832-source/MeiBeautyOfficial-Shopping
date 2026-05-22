package com.amy.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.amy.demo.security.handler.MyLoginFailureHandler;

@Configuration
public class SecurityConfig {
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http
		  		, MyLoginFailureHandler failureHandler
		  		) throws Exception {
		      http
		          .authorizeHttpRequests(auth -> auth
		              .requestMatchers("/login/**",
		                      "/home/**",
		                      "/assets/css/**",
		                      "/assets/js/**",
		                      "/assets/images/**", "/api/restController/**", "/assets/fonts/**").permitAll()
		              .anyRequest().authenticated()
		          )

		          .formLogin(form -> form
		          	.loginPage("/login/index")
		          	.loginProcessingUrl("/login/doLogin") 

		          		.defaultSuccessUrl("/home/index", true)
		          		.failureHandler(failureHandler)
		              .permitAll()
		          )

		          .logout(logout -> logout
		              .logoutSuccessUrl("/login/index")
		          )
		          .csrf(csrf -> csrf.disable());
		      return http.build();
		  }
	  
	  
	  @Bean
	  public PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	  }
}