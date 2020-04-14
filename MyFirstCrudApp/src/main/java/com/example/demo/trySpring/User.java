package com.example.demo.trySpring;

import lombok.Data;

@Data
// lombokの機能でsetter,getterの自動生成
public class User {
	private int userId;
	private String userName;
	private int age;
	
}
