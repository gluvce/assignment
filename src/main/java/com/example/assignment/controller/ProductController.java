package com.example.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Data;
import com.example.assignment.model.Product;
import com.example.assignment.model.ProductQueryParameters;
import com.example.assignment.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("/product")
	public Data getProduct(@Validated ProductQueryParameters query) {
		List<Product> data = productRepository.search(query);
		Data result = new Data();
		result.setData(data);
		return result;
	}

}
