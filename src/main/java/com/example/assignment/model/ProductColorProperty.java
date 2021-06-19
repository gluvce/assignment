package com.example.assignment.model;

public class ProductColorProperty extends ProductProperty {
	private String color;

	public ProductColorProperty() {
		super();
	}
	
	public ProductColorProperty(String color) {
		super(PropertyType.color);
		this.color = color;
	}

	public String getValue() {
		return color;
	}

	public void setValue(String value) {
		this.color = value;
	}
}
