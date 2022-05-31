package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Clothe;
import com.example.repository.ClotheRepository;

/**
 * 衣類情報の業務処理を行うサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class ClotheService {

	@Autowired
	private ClotheRepository clotheRepository;

	/**
	 * 色と性別の条件に合う衣類情報を取得します.
	 * 
	 * @param color  色
	 * @param gender 性別
	 * @return 衣類情報一覧
	 */
	public List<Clothe> searchByColorAndGender(String color, Integer gender) {
		return clotheRepository.findByColorAndGender(color, gender);
	}

}
