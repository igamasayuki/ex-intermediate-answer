package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Hotel;
import com.example.form.HotelSearchForm;
import com.example.service.HotelService;

/**
 * ホテル情報の処理制御を行うコントローラー.
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
	 * ホテル検索画面をフォワードする処理を行います.
	 * 
	 * @param form フォーム
	 * @return ホテル検索画面
	 */
	@RequestMapping("/search")
	public String search(HotelSearchForm form) {
		return "hotel/hotel-search";
	}

	/**
	 * 指定した価格以下のホテル情報を取得しフォワードします. 指定した値が空の場合は、ホテル情報を全件取得してフォワードします。
	 * 
	 * @param form  フォーム
	 * @param model リクエストスコープ
	 * @return ホテル検索画面
	 */
	@RequestMapping("/output")
	public String output(HotelSearchForm form, Model model) {

		hotelList = hotelService.searchByLessThanPrice(form.getPrice());
		model.addAttribute("hotelList", hotelList);

		return "hotel/hotel-search";
	}

}
