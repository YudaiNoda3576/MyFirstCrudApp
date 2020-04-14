package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
//	コンテンツ部分にホーム画面を表示する文字列の登録
	@GetMapping("/home") 
	public String getHome(Model model) {
		model.addAttribute("contents", "login/home :: home_contents");
		return "login/homeLayout";
	}
//	ユーザー一覧画面のGETメソッド
	@GetMapping("/userList")
	public String getUserList(Model model) {
//		コンテンツ部分にユーザー一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/userList :: userList_contents");
//		ユーザー一覧の生成
		List<User>userList = userService.selectMany();
//		Modelにユーザーリストを登録
		model.addAttribute("userList", userList);
//		データ件数を取得
		int count = userService.count();
		model.addAttribute("userListCount", count);
		
		return"login/homeLayout";
	}
//	動的URL→GetMappingやPostMappingの値に/{<変数名>}をつける
//	@PathVariable→渡されてきたパスの値を変数に入れることができる
//	ユーザー詳細画面のGET用メソッド
	@GetMapping("/userDetail/{id}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, 
			@PathVariable("id")String userId) {
//		ユーザーID確認（デバッグ）
		System.out.println("userId = " + userId);
//		コンテンツ部分にユーザー詳細を表示する文字列を登録
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
//		ユーザーIDのチェック
		if(userId != null && userId.length() > 0) {
//			ユーザー情報の取得
			User user = userService.selectOne(userId);
//			Userクラスをフォームクラスに変換
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			form.setPassword(user.getPassword());
			form.setAge(user.getAge());
//			Modelに登録
			model.addAttribute("signupForm", form);
		}
		return "login/homeLayout";
	}
//	ボタン名によるメソッド判定 postmapingの書き方に注目　更新処理メソッドと削除処理メソッドを分けるため
//どちらのボタンを押しても/userDetailにPOSTするようになっているので、判断する為にparams属性を使用
//	htmlのname="update"の値を入れることで、コントローラークラスのメソッドを分けることができる
//	ユーザー更新用処理
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");
//		ユーザーインスタンスの生成
		User user = new User();
//		フォームクラスをUserクラスに変換
		user.setUserId(form.getUserId());
		user.setUserName(form.getUserName());
		user.setPassword(form.getPassword());
		user.setAge(form.getAge());
//		更新実行
		boolean result = userService.updateOne(user);
		
		if(result == true) {
			model.addAttribute("result", "更新成功");
		} else {
			model.addAttribute("result", "更新失敗");
		}
//		ユーザー一覧画面の表示
		return getUserList(model);
	}
	
//	ユーザー削除処理
	@PostMapping(value = "/userDetail", params = "delete")
	public String postUserDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除ボタンの処理");
//		削除実行
		boolean result = userService.deleteOne(form.getUserId());
		
		if(result == true) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result", "削除失敗");
		}
//		ユーザー一覧画面を表示
		return getUserList(model);
	}
	
//	ログアウト用メソッド
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:/login";
	}
}