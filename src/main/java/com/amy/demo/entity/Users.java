package com.amy.demo.entity;


import jakarta.persistence.Id;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;

    @Column(name = "name") 
    private String name;

    @Column(name = "pwd") 
    private String pwd;

    @Column(name = "account_expired") 
    private boolean accountExpired;
    
    @Column(name = "account_locked") 
    private boolean accountLocked;
    
    @Column(name = "credentials_expired") 
    private boolean credentialsExpired;
    
    @Column(name = "enabled") 
    private boolean enabled;
    
    @Column(name = "token") 
    private String token;
    
    @Column(name = "create_date") 
    private Date createDate;
    
    @Column(name = "update_date") 
    private Date updateDate;
    
    @Column(name = "creator")
    private String creator;
    
    @Column(name = "modifier")
    private String modifier;
    
}

