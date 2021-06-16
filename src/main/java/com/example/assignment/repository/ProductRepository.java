package com.example.assignment.repository;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.assignment.model.Product;
import com.example.assignment.model.ProductColorProperty;
import com.example.assignment.model.ProductGbLimitProperty;
import com.example.assignment.model.ProductProperty;
import com.example.assignment.model.ProductQueryParameters;
import com.example.assignment.model.ProductType;
import com.example.assignment.model.PropertyType;

@Repository
public class ProductRepository {

	private final List<Product> products;

	public ProductRepository() {
		products = populateProducts();
	}

	public List<Product> getAllProducts() {
		return products;
	}

	public List<Product> search(ProductQueryParameters query) {
		List<Product> result = products;
		if (query.getCity() != null) {
			result = result.stream().filter(p -> p.getStoreAddress().contains(query.getCity())).collect(toList());
		}
		if (query.getType() != null) {
			result = result.stream().filter(p -> p.getType() == query.getType()).collect(toList());
		}
		if (query.getProperty() != null) {
			if (query.getProperty().getColor() != null) {
				result = result.stream().filter(p -> p.getProperties().getType() == PropertyType.color
						&& ((ProductColorProperty) p.getProperties()).getValue().equals(query.getProperty().getColor()))
						.collect(toList());
			}
			if (query.getProperty().getGbLimitMin() != null) {
				result = result.stream()
						.filter(p -> p.getProperties().getType() == PropertyType.gb_limit
								&& ((ProductGbLimitProperty) p.getProperties()).getLimit() >= query.getProperty()
										.getGbLimitMin())
						.collect(toList());
			}
			if (query.getProperty().getGbLimitMax() != null) {
				result = result.stream()
						.filter(p -> p.getProperties().getType() == PropertyType.gb_limit
								&& ((ProductGbLimitProperty) p.getProperties()).getLimit() <= query.getProperty()
										.getGbLimitMax())
						.collect(toList());
			}
		}
		if (query.getMin_price() != null) {
			result = result.stream().filter(p -> p.getPrice().compareTo(query.getMin_price()) >= 0).collect(toList());
		}
		if (query.getMax_price() != null) {
			result = result.stream().filter(p -> p.getPrice().compareTo(query.getMax_price()) <= 0).collect(toList());
		}
		return result;
	}

	private List<Product> populateProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data.csv"));
			br.readLine();// skip header
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Product p = new Product();
				p.setType(ProductType.valueOf(data[0]));
				p.setProperties(setProductParameter(data[1]));
				p.setPrice(new BigDecimal(data[2]));
				p.setStoreAddress(setAddress(data[3], data[4]));
				products.add(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}

	private String setAddress(String firstPart, String secondPart) {
		return firstPart.substring(1) + ", " + secondPart.substring(0, secondPart.length() - 1);
	}

	private ProductProperty setProductParameter(String data) {
		if (data.contains(PropertyType.color.getCode())) {
			return new ProductColorProperty(data.substring(data.indexOf(":") + 1, data.length()));
		} else {
			return new ProductGbLimitProperty(Integer.parseInt(data.substring(data.indexOf(":") + 1, data.length())));
		}
	}
}
