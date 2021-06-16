package com.example.assignment.model;

public enum PropertyType {
	color("color"), gb_limit("gb_limit");

	private String code;

	public String getCode() {
		return code;
	}

	PropertyType(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.getCode();
	}
}
