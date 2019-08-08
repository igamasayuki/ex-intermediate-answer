package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Team;
import com.example.repository.TeamRepository;

/**
 * 球団情報の業務処理を行うサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	
	/**
	 * 球団情報を発足日順に全件取得します.
	 * 
	 * @return 球団情報一覧
	 */
	public List<Team> showList() {
		return teamRepository.findAll();
	}
	
	
	/**
	 * IDに該当する球団情報を取得します.
	 * 
	 * @param id ID
	 * @return 球団情報
	 */
	public Team showDetail(Integer id) {
		return teamRepository.load(id);
	}
	
}
