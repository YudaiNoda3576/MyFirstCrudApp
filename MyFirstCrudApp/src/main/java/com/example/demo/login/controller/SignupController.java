package com.example.demo.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
		return "login/signup";
	}
	
    /** ユーザー登録画面のPOSTメソッド用処理. **/
    @PostMapping("/signup")
//    ModeladdAttributeで自動でModelクラスに登録
    public String postSignUp(@ModelAttribute SignupForm form, Model model) {
//    ユーザー登録用のPOST用コントローラー
//    formの中身をコンソールに出力
    System.out.println(form);    	
//    insert用変数
    User user = new User();
//    ユーザーID
    user.setUserId(form.getUserId());
//    パスワード
    user.setPassword(form.getPassword());
//    ユーザー名
    user.setUserName(form.getUserName());
//    年齢
    user.setAge(form.getAge());
//    ユーザー登録処理
    boolean result = userService.insert(user);
//    ユーザー登録結果の判定
    if(result == true) {
    	System.out.println("insert成功");
    } else {
    	System.out.println("insert失敗");
    }
	// login.htmlにリダイレクト
	return "redirect:/login";
    }
  }
