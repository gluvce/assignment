package com.example.assignment.repository;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.example.assignment.model.Product;
import com.example.assignment.model.ProductColorProperty;
import com.example.assignment.model.ProductGbLimitProperty;
import com.example.assignment.model.ProductProperty;
import com.example.assignment.model.ProductType;
import com.example.assignment.model.PropertyType;
import com.example.assignment.model.query.ProductQueryParameters;

@Repository
public class ProductRepository {

	private final String SEPARATOR = ",";
	private final int EXPECTED = 5;

	// this is my "in memory DB"
	// we can also use H2 DB and then JPA and spring data to filter
	private final List<Product> products;

	public ProductRepository() {
		products = populateProducts();
	}

	public List<Product> search(ProductQueryParameters query) {
		Stream<Product> result = products.stream();
		if (query.getCity() != null) {
			result = result.filter(p -> p.getStoreAddress().contains(query.getCity()));
		}
		if (query.getType() != null) {
			result = result.filter(p -> p.getType() == query.getType());
		}
		if (query.getProperty() != null) {
			if (query.getProperty().getColor() != null) {
				result = result.filter(p -> p.getProperties().getType() == PropertyType.color
						&& ((ProductColorProperty) p.getProperties()).getValue()
								.equals(query.getProperty().getColor()));
			}
			if (query.getProperty().getGbLimitMin() != null) {
				result = result.filter(p -> p.getProperties().getType() == PropertyType.gb_limit
						&& ((ProductGbLimitProperty) p.getProperties()).getLimit() >= query.getProperty()
								.getGbLimitMin());
			}
			if (query.getProperty().getGbLimitMax() != null) {
				result = result.filter(p -> p.getProperties().getType() == PropertyType.gb_limit
						&& ((ProductGbLimitProperty) p.getProperties()).getLimit() <= query.getProperty()
								.getGbLimitMax());
			}
		}
		if (query.getMinPrice() != null) {
			result = result.filter(p -> p.getPrice().compareTo(query.getMinPrice()) >= 0);
		}
		if (query.getMaxPrice() != null) {
			result = result.filter(p -> p.getPrice().compareTo(query.getMaxPrice()) <= 0);
		}
		return result.collect(toList());
	}

	private List<Product> populateProducts() {
		List<Product> products = new ArrayList<Product>();

		String fileName = "data.csv";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(SEPARATOR);
				// check if first line is a header
				if (i == 0 && isHeader(data)) {
					i++;
					continue;
				}
				// if data is valid parse it
				if (isValidData(i, data)) {
					Product p = new Product();
					p.setType(ProductType.valueOf(data[0]));
					p.setProperties(setProductParameter(data[1]));
					p.setPrice(new BigDecimal(data[2]));
					p.setStoreAddress(setAddress(data[3], data[4]));
					products.add(p);
				}
				i++;

			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return products;
	}

	private boolean isHeader(String[] data) {
		// simple check for the current example
		if (data.length != EXPECTED - 1) {
			return false;
		}
		return true;
	}

	private boolean isValidData(long lineNumber, String[] data) {
		// simple validation, need to be extended (like validation of the data type)
		if (data.length != EXPECTED) {
			System.out.println("Missing attributes on line: " + lineNumber);
			return false;
		}
		return true;

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

	public List<Product> searchOneLiner(ProductQueryParameters query) {
//		Everything in one line (not very readable)
		return products.stream()
				.filter(p -> (query.getCity() != null ? p.getStoreAddress().contains(query.getCity()) : true)
						&& (query.getType() != null ? p.getType() == query.getType() : true)
						&& (query.getMinPrice() != null ? p.getPrice().compareTo(query.getMinPrice()) >= 0 : true)
						&& (query.getMaxPrice() != null ? p.getPrice().compareTo(query.getMaxPrice()) <= 0 : true)
						&& (query.getProperty() != null
								? ((query.getProperty()
										.getColor() != null
												? p.getProperties().getType() == PropertyType.color
														&& ((ProductColorProperty) p.getProperties()).getValue()
																.equals(query.getProperty().getColor())
												: true)
										&& (query.getProperty().getGbLimitMin() != null
												? p.getProperties().getType() == PropertyType.gb_limit
														&& ((ProductGbLimitProperty) p.getProperties())
																.getLimit() >= query.getProperty().getGbLimitMin()
												: true)
										&& (query.getProperty().getGbLimitMax() != null
												? p.getProperties().getType() == PropertyType.gb_limit
														&& ((ProductGbLimitProperty) p.getProperties())
																.getLimit() <= query.getProperty().getGbLimitMax()
												: true))
								: true))
				.collect(toList());
	}
}
