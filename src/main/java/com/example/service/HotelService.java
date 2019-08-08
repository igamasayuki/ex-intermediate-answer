package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Hotel;
import com.example.repository.HotelRepository;

/**
 * ホテル情報の業務処理を行うサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class HotelService {
	
	
	@Autowired
	private HotelRepository hotelRepository;
	
	
	/**
	 * 指定した価格以下のホテル情報を取得します.
	 * 引数の値が空の場合はホテル情報を全件取得します。
	 * 
	 * @param price 価格
	 * @return ホテル情報
	 */
	public List<Hotel> searchByLessThanPrice(String price) {
		
		if ("".equals(price)) {
			return hotelRepository.findAll();
		} else {
			return hotelRepository.findByLessThanPrice(Integer.parseInt(price));
		}
		
	}

}
