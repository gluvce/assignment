package com.example.assignment.model;

public abstract class ProductProperty {
	private PropertyType type;

	public ProductProperty() {
	}

	public ProductProperty(PropertyType type) {
		this.type = type;
	}

	public PropertyType getType() {
		return type;
	}

	public void setType(PropertyType type) {
		this.type = type;
	}
}
