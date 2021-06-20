package com.example.assignment;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTOTest {

	private ProductTypeTest type;
	private String properties;
	private BigDecimal price;
	private String storeAddress;

	public ProductDTOTest() {
	}

	public ProductDTOTest(ProductTypeTest type, String properties, BigDecimal price, String storeAddress) {
		this.type = type;
		this.properties = properties;
		this.price = price;
		this.storeAddress = storeAddress;
	}

	public ProductTypeTest getType() {
		return type;
	}

	public void setType(ProductTypeTest type) {
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
