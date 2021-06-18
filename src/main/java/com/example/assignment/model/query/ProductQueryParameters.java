package com.example.assignment.model.query;

import java.math.BigDecimal;

import com.example.assignment.model.ProductType;

public class ProductQueryParameters {

	private ProductType type;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private String city;
	private ProductQueryProperty property;

	public ProductQueryParameters() {

	}

	public ProductQueryParameters(ProductType type, BigDecimal minPrice, BigDecimal maxPrice, String city,
			ProductQueryProperty property) {
		this.type = type;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.city = city;
		this.property = property;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMin_price(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMax_price(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
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
