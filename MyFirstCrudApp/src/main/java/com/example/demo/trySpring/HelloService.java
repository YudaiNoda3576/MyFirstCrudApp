package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//リポジトリークラスなどを使った色々なサービスを提供。MVCで言うとModel.
@Service
public class HelloService {
	@Autowired
//	HelloRepositoryクラスを使うため
	private HelloRepository helloRepository;
	
	public User findOne(int id) {
//		1件検索
		Map<String, Object> map = helloRepository.findOne(id);
//		Mapから値を取得 Mapのgetメソッドにテーブルのフィールド名を指定すれば値を取得できる。
		int userId = (Integer)map.get("user_id");
		String userName = (String)map.get("user_name");
		int age = (Integer)map.get("age");
//		Mapから取得した値をUserクラスのインスタンスにセット
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setAge(age);
		
		return user;
	}
}
