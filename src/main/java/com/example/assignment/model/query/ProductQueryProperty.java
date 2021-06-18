package com.example.assignment.model.query;

public class ProductQueryProperty {

	private String color;
	private Integer gbLimitMin;
	private Integer gbLimitMax;

	public ProductQueryProperty() {

	}

	public ProductQueryProperty(String color, Integer gbLimitMin, Integer gbLimitMax) {
		this.color = color;
		this.gbLimitMin = gbLimitMin;
		this.gbLimitMax = gbLimitMax;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getGbLimitMin() {
		return gbLimitMin;
	}

	public void setGb_limit_min(Integer gbLimitMin) {
		this.gbLimitMin = gbLimitMin;
	}

	public Integer getGbLimitMax() {
		return gbLimitMax;
	}

	public void setGb_limit_max(Integer gbLimitMax) {
		this.gbLimitMax = gbLimitMax;
	}
}
