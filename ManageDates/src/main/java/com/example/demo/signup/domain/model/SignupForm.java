package com.example.demo.signup.domain.model;

import lombok.Data;

@Data
public class SignupForm {
	private String countId;
	private String dateId;
	private String dateName;
	private int calcYear;
	private int calcMonth;
	private int calcDay;
}
