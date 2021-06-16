package com.example.assignment.model;

import java.math.BigDecimal;

public class ProductQueryParameters {
	private ProductType type;
	private BigDecimal min_price;
	private BigDecimal max_price;
	private String city;

	private ProductQueryProperty property;

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public BigDecimal getMin_price() {
		return min_price;
	}

	public void setMin_price(BigDecimal min_price) {
		this.min_price = min_price;
	}

	public BigDecimal getMax_price() {
		return max_price;
	}

	public void setMax_price(BigDecimal max_price) {
		this.max_price = max_price;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ProductQueryProperty getProperty() {
		return property;
	}

	public void setProperty(ProductQueryProperty property) {
		this.property = property;
	}

}
