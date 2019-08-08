package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Hotel;

/**
 * hotelsテーブルを操作するリポジトリ.
 * 
 * @author igamasayuki
 *
 */
@Repository
public class HotelRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** DBテーブルネーム */
	private static final String TABLE_HOTELS = "hotels";


	/**
	 * Hotelオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs, i) -> {

		Hotel hotel= new Hotel();
		hotel.setId(rs.getInt("id"));
		hotel.setAreaName(rs.getString("area_name"));
		hotel.setHotelName(rs.getString("hotel_name"));
		hotel.setAddress(rs.getString("address"));
		hotel.setNearestStation(rs.getString("nearest_station"));
		hotel.setPrice(rs.getInt("price"));
		hotel.setParking(rs.getString("parking"));

		return hotel;
	};

	/**
	 * ホテル情報を価格順(降順)で取得します.
	 * 
	 * @return ホテル情報一覧
	 */
	public List<Hotel> findAll() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, area_name, hotel_name, address, nearest_station, price, parking FROM "+ TABLE_HOTELS + " ORDER BY price DESC");

		List<Hotel> hotelList = template.query(sql.toString(), HOTEL_ROW_MAPPER);

		return hotelList;
	}

	/**
	 * 指定した価格以下のホテル情報を取得します。
	 * 
	 * @param price 価格
	 * @return ホテル情報
	 */
	public List<Hotel> findByLessThanPrice(Integer price) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, area_name, hotel_name, address, nearest_station, price, parking FROM " + TABLE_HOTELS + " WHERE price <= :price ORDER BY price DESC");

		SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);

		List<Hotel> hotelList = template.query(sql.toString(), param, HOTEL_ROW_MAPPER);

		return hotelList;
	}



}
