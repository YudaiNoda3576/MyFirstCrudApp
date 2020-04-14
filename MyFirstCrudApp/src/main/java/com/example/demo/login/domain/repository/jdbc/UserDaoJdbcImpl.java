package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
//リポジトリ
//インターフェイスを実装したクラス

@Repository
public class UserDaoJdbcImpl implements UserDao {
	@Autowired 
	JdbcTemplate jdbc;
//	Userテーブルの件数を取得
	@Override
	public int count() throws DataAccessException {
//		Objectの取得
//		カウントの結果、カラムを１つだけ取得してくる場合は、queryForMapメソッドを使う。
//		第一引数にSQL文、第二引数に戻り値のオブジェクトのclassを指定
//		全件取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT(*)FROM m_user", Integer.class);
		
		return count;
	}
//	Userテーブルにデータを一件insert
	@Override
	public int insertOne(User user) throws DataAccessException {
//		updateメソッド　第一引数にSQL文、第二引数にPreparedStatement
//		戻り値には登録したレコード数が返ってくる
		int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
				+ " user_name, "
				+ " password, "
				+ " age )"
//				これが無いとSQLのデータ数を参照できない↓　PreparedStatement　SQL文の?に入れる変数を第二引数にセット
				+ " VALUES(?,?,?,?) "
				,user.getUserId()
				,user.getUserName()
				,user.getPassword()
				,user.getAge());				
		return rowNumber;
	}
//	Userテーブルのデータを一件取得
	@Override
	public User selectOne(String userId) throws DataAccessException {
//		１件取得
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user"
				+ " WHERE user_id = ?"
				,userId);
//		結果返却用の変数
		User user = new User();
//		取得したデータを結果返却用の変数にセットしていく
		user.setUserId((String)map.get("user_id"));
		user.setUserName((String)map.get("user_name"));
		user.setPassword((String)map.get("password"));
		user.setAge((Integer)map.get("age"));
		
		return user;
	}

//	Userテーブルの全データをを取得
	@Override
	public List<User> selectMany() throws DataAccessException {
//		複数件のselect→queryForList戻り値の方にはList<Map<String, Object>>を指定
//		Listが行を表し、Mapが列を示す
//		MapのGetメソッドでテーブルのカラム名を指定することで値を取得
//		拡張for文を使ってList<Map<String, Object>>をList<User>に変換
//		m_userテーブルのデータを全件取得
		List<Map<String, Object>>getList = jdbc.queryForList("SELECT * FROM m_user");
//		結果返却用の変数
		List<User>userList = new ArrayList<>();
//		取得したデータをListに格納していく
		for(Map<String, Object> map : getList) {
//			 Userインスタンスの生成
			User user = new User();
//			インスタンスに取得したデータをセットする
			user.setUserId((String)map.get("user_id"));
			user.setUserName((String)map.get("user_name"));
			user.setPassword((String)map.get("password"));
			user.setAge((Integer)map.get("age"));
//			結果返却用のListに追加
			userList.add(user);
		}
		return userList;
	}
//	Userテーブルを一件更新
	@Override
	public int updateOne(User user) throws DataAccessException {
//		一件更新
		int rowNumber = jdbc.update("UPDATE m_user"
				+ " SET"
				+ " user_name = ?,"
				+ " password = ?,"
				+ " age = ?"
//				↓この記述無いと、テーブルのデータ全て更新される
				+ " WHERE user_id = ?"
				,user.getUserName()
				,user.getPassword()
				,user.getAge()				
				,user.getUserId());
		return rowNumber;
	}
//	Userテーブルを一件削除
	@Override
	public int deleteOne(String userId) throws DataAccessException {
//		一件削除
		int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);
			return rowNumber;
		}
	}
	

