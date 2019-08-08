package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Team;

/**
 * teamsテーブルを操作するリポジトリ.
 * 
 * @author igamasayuki
 *
 */
@Repository
public class TeamRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** DBテーブルネーム */
	private static final String TABLE_TEAMS ="teams";
	
	
	/**
	 * Teamオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
		
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		
		return team;
	};
	
	
	/**
	 * 球団情報を発足日順で全件取得します.
	 * 
	 * @return 球団情報一覧
	 */
	public List<Team> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, league_name, team_name, headquarters, inauguration, history FROM " + TABLE_TEAMS+ " ORDER BY inauguration");
		
		List<Team> teamList = template.query(sql.toString(), TEAM_ROW_MAPPER);
		
		return teamList;
	}
	
	
	/**
	 * 主キーから球団情報を取得します.
	 * 
	 * @param id ID
	 * @return 球団情報
	 */
	public Team load(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, league_name, team_name, headquarters, inauguration, history FROM " + TABLE_TEAMS + " WHERE id=:id");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Team team = template.queryForObject(sql.toString(), param, TEAM_ROW_MAPPER);
		
		return team;
	}

}
