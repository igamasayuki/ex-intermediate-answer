package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Team;
import com.example.service.TeamService;

/**
 * 球団情報を操作するコントローラ.
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
	 * 球団一覧を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return 球団情報一覧画面
	 */
	@GetMapping("/showList")
	public String showList(Model model) {

		List<Team> teamList = teamService.showList();
		model.addAttribute("teamList", teamList);
		return "team/team-list";
	}

	/**
	 * 球団詳細を表示する.
	 * 
	 * @param id    ID
	 * @param model リクエストスコープ
	 * @return 球団情報詳細画面
	 */
	@GetMapping("/showDetail")
	public String showDetail(Integer id, Model model) {

		Team team = teamService.showDetail(id);
		model.addAttribute("team", team);

		return "team/team-detail";
	}

}
