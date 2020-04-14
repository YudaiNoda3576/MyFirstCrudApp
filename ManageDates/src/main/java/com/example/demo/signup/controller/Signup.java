package com.example.demo.signup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.signup.domain.model.SignupForm;
@Controller
public class Signup {
	@GetMapping("/signup")
//	@ModelAttributeで自動でModelクラスに登録　キー名はクラスの最初の大文字を小文字に変えたもの
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
//		templatesからのパスで指定しているので、　signupフォルダ配下のsignup.javaを指定している
			return "signup/signup";
		}
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute SignupForm form, Model model) {
		return "redirect:/signup";
	}
}