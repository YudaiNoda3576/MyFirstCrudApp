package com.example.demo.trySpring;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//レポジトリクラス　DBへのCRUD処理を行い、その結果を返す。MVCモデルで言うとModelに該当
@Repository
public class HelloRepository {
	@Autowired
//	JdbcTemplateを使うときにつける↑　インスタンスを生成しているイメージ
	private JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> findOne(int id) {
		String query = "SELECT "
				+ " user_id, "
				+ " user_name, "
				+ " age "
				+ " FROM m_user "
				+ " WHERE user_id = ? ";
		Map<String, Object> m_user = jdbcTemplate.queryForMap(query, id);
		
		return m_user;
	}
}
