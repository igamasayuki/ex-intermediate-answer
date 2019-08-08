package com.example.form;

/**
 * 検索時に使用するフォームクラス.
 * 
 * @author igamasayuki
 *
 */
public class ClotheSearchForm {
	
	/** 性別 */
	private String gender;
	
	/** 色 */
	private String color;


	@Override
	public String toString() {
		return "ClotheForm [gender=" + gender + ", color=" + color + "]";
	}


	public Integer getIntGender() {
		return Integer.parseInt(this.gender);
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}


}
