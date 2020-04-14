package com.example.demo.login.domain.repository.mybatis;

import java.util.List;
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

import com.example.demo.login.domain.model.User;


//ポイント：mybatiでSQLを実行するアノテーション
@Mapper
public interface UserMapper {
//	変数の指定　SQLを実行する為に、@Insertや＠ Selectをつける。そして、#{変数名}とすることで使える
//	登録用メソッド
//	@Insert("INSERT INTO m_user("
//			+ " user_id,"
//			+ " password,"
//			+ " user_name,"
//			+ " age)"
//			+ " VALUES ("
//			+ " #{userId},"
//			+ " #{password},"
//			+ " #{user_name},"
//			+ " #{age})")
	public boolean insert(User user);
	
//ポイント：カラム名　テーブルの絡む名とクラスのフィールド名が一致していない場合はSQL文にAS句を使ってカラム名を変更する
//	一件検索用メソッド
//	@Select("SELECT user_id AS userId,"
//			+ "password,"
//			+ "user_name AS userName,"
//			+ "age")
	public User selectOne(String userId);
//	全件検索用メソッド
//	@Select("SELECT user_id AS userId,"
//			+ "password,"
//			+ "user_name AS userName,"
//			+ "age")
	public List<User> selectMany();
//	一件更新用メソッド
//	@Update("UPDATE m_user SET"
//			+ " password = #{password},"
//			+ " user_name = #{userName},"
//			+ " age = #{age}")
	public boolean updateOne(User user);
//	一件削除用メソッド
//	@Delete("DELETE FROM m_user WHERE user_id = #{userId}")
	public boolean deleteOne(String userId);
}
