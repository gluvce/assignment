package com.example.assignment.model;

public class ProductQueryProperty {
	private String color;
	private Integer gb_limit_min;
	private Integer gb_limit_max;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getGbLimitMin() {
		return gb_limit_min;
	}

	public void setGbLimitMin(Integer gbLimitMin) {
		this.gb_limit_min = gbLimitMin;
	}

	public Integer getGbLimitMax() {
		return gb_limit_max;
	}

	public void setGbLimitMax(Integer gbLimitMax) {
		this.gb_limit_max = gbLimitMax;
	}
}
