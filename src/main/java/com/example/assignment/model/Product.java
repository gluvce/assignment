package com.example.assignment.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

	private ProductType type;
	private ProductProperty properties;
	private BigDecimal price;
	private String storeAddress;

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public ProductProperty getProperties() {
		return properties;
	}

	public void setProperties(ProductProperty properties) {
		this.properties = properties;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@JsonProperty("store_address")
	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
}
