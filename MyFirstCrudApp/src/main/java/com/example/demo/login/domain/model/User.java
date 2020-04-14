package com.example.demo.login.domain.model;

import lombok.Data;


@Data
public class User {
//	なぜuserIdはString型で受け取っているのか？
	private String userId;
	private String userName;
	private String password;
	private int age;
}