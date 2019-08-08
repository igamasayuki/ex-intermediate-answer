package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Clothe;
import com.example.form.ClotheSearchForm;
import com.example.service.ClotheService;

/**
 * 衣類情報の処理制御を行うコントローラ.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/clothe")
public class ClotheController {


	@Autowired
	private ClotheService clotheService;
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public ClotheSearchForm setUpSearchForm() {
		return new ClotheSearchForm();
	}


	/**
	 * 衣類検索画面へフォワードする処理を行います.
	 * 
	 * @param model リクエストスコープ
	 * @return 衣類検索画面
	 */
	@RequestMapping("/search")
	public String search(Model model) {

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
	 * 色と性別の条件に一致する衣類情報を取得します。
	 * 
	 * @param color 色
	 * @param gender 性別
	 * @param model リクエストスコープ
	 * @return 衣類検索画面
	 */
	@RequestMapping("/output")
	public String output(ClotheSearchForm form, Model model) {
		List<Clothe> clotheList = clotheService.searchByColorAndGender(form.getColor(), form.getIntGender());

		model.addAttribute("clotheList", clotheList);

		return search(model);
	}

}
