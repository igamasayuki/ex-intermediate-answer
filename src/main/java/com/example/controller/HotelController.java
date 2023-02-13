package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Hotel;
import com.example.form.HotelSearchForm;
import com.example.service.HotelService;

/**
 * ホテル情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {

//	/**
//	 * 使用するフォームオブジェクトをリクエストスコープに格納します.
//	 * 
//	 * @return フォーム
//	 */
//	@ModelAttribute
//	public HotelSearchForm setUpForm() {
//		return new HotelSearchForm();
//	}

	@Autowired
	private HotelService hotelService;

	/** ホテルオブジェクトを格納するリスト */
	private List<Hotel> hotelList;

	/**
	 * ホテル検索画面を表示する.
	 * 
	 * @param form フォーム
	 * @return ホテル検索画面
	 */
	@GetMapping("")
	public String index(HotelSearchForm form) {
		return "hotel/hotel-search";
	}

	/**
	 * 指定した価格以下のホテル情報を検索する. <br>
	 * 
	 * 指定した値が空の場合は、ホテル情報を全件検索します。
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return ホテル検索画面
	 */
	@PostMapping("/search")
	public String search(HotelSearchForm form, Model model) {

		hotelList = hotelService.searchByLessThanPrice(form.getPrice());
		model.addAttribute("hotelList", hotelList);

		return "hotel/hotel-search";
	}

}
