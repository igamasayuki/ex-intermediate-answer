package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Team;
import com.example.service.TeamService;

/**
 * 球団情報の処理の制御を行うコントローラ.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teamService;


	/**
	 * 球団情報一覧を出力する処理を行います.
	 * 
	 * @param model リクエストスコープ
	 * @return 球団情報一覧画面
	 */
	@RequestMapping("/list")
	public String teamList(Model model) {
		
		List<Team> teamList = teamService.showList();
		model.addAttribute("teamList", teamList);
		return "team/team-list";
	}


	/**
	 * 球団の詳細を出力する処理を行います.
	 * 
	 * @param id ID
	 * @param model リクエストスコープ
	 * @return 球団情報詳細画面
	 */
	@RequestMapping("/detail")
	public String teamDeatil(Integer id, Model model) {
		
		Team team = teamService.showDetail(id);
		model.addAttribute("team", team);
		
		return "team/team-detail";
	}

}
