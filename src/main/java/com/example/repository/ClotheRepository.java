package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Clothe;

/**
 * clothesテーブルを操作するリポジトリ.
 * 
 * @author igamasayuki
 *
 */
@Repository
public class ClotheRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** DBテーブルネーム */
	private static final String TABLE_CLOTHES = "clothes";
	
	
	/**
	 * Clotheオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Clothe> CLOTHE_ROW_MAPPER = (rs, i) -> {
		
		Clothe clothe = new Clothe();
		clothe.setId(rs.getInt("id"));
		clothe.setCategory(rs.getString("category"));
		clothe.setGenre(rs.getString("genre"));
		clothe.setGender(rs.getInt("gender"));
		clothe.setColor(rs.getString("color"));
		clothe.setPrice(rs.getInt("price"));
		clothe.setSize(rs.getString("size"));
		
		return clothe;
	};
	
	
	/**
	 * 色と性別の条件に合う衣類情報を取得します.
	 * 
	 * @param color 色
	 * @param gender 性別
	 * @return 衣類情報一覧
	 */
	public List<Clothe> findByColorAndGender(String color, Integer gender) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, category, genre, gender, color, price, size FROM " + TABLE_CLOTHES + " WHERE color=:color AND gender=:gender");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("color", color).addValue("gender", gender);
		
		List<Clothe> clotheList = template.query(sql.toString(), param, CLOTHE_ROW_MAPPER);
		
		return clotheList;
	}
	
	
	
}
