package com.example.assignment.dto;

import java.math.BigDecimal;

import com.example.assignment.model.ProductProperty;
import com.example.assignment.model.ProductType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

	private ProductType type;
	private String properties;
	private BigDecimal price;
	private String storeAddress;

	public ProductDTO() {
	}

	public ProductDTO(ProductType type, String properties, BigDecimal price, String storeAddress) {
		this.type = type;
		this.properties = properties;
		this.price = price;
		this.storeAddress = storeAddress;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
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
