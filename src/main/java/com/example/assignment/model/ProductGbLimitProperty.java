package com.example.assignment.model;

public class ProductGbLimitProperty extends ProductProperty {
	private Integer limit;

	public ProductGbLimitProperty() {
		super();
	}
	
	public ProductGbLimitProperty(Integer limit) {
		super(PropertyType.gb_limit);
		this.limit = limit;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer value) {
		this.limit = value;
	}
}
