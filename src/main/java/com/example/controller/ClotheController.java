package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Clothe;
import com.example.form.ClotheSearchForm;
import com.example.service.ClotheService;

/**
 * 衣類情報を操作するコントローラ.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/clothe")
public class ClotheController {

	@Autowired
	private ClotheService clotheService;

//	/**
//	 * 使用するフォームオブジェクトをリクエストスコープに格納します.
//	 * 
//	 * @return フォーム
//	 */
//	@ModelAttribute
//	public ClotheSearchForm setUpSearchForm() {
//		return new ClotheSearchForm();
//	}

	/**
	 * 衣類検索画面を表示する.
	 * 
	 * @param form フォーム
	 * @param model リクエストスコープ
	 * @return 衣類検索画面
	 */
	@GetMapping("")
	public String index(ClotheSearchForm form, Model model) {

		Map<Integer, String> genderMap = new LinkedHashMap<>();
		genderMap.put(0, "Man");
		genderMap.put(1, "Woman");
		model.addAttribute("genderMap", genderMap);

		Map<Integer, String> colorMap = new LinkedHashMap<>();
		colorMap.put(1, "赤");
		colorMap.put(2, "青");
		colorMap.put(3, "白");
		colorMap.put(4, "黄");
		model.addAttribute("colorMap", colorMap);

		return "clothe/clothe-search";
	}

	/**
	 * 衣類検索をする.
	 * 
	 * @param color  色
	 * @param gender 性別
	 * @param model  リクエストスコープ
	 * @return 衣類検索画面
	 */
	@PostMapping("/search")
	public String search(ClotheSearchForm form, Model model) {
		System.out.println(form);
		List<Clothe> clotheList = clotheService.searchByColorAndGender(form.getColor(), form.getIntGender());

		model.addAttribute("clotheList", clotheList);

		return index(form, model);
	}

}
