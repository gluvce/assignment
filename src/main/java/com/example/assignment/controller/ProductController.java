package com.example.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.dto.DataDTO;
import com.example.assignment.dto.ProductDTO;
import com.example.assignment.model.Product;
import com.example.assignment.model.ProductColorProperty;
import com.example.assignment.model.ProductGbLimitProperty;
import com.example.assignment.model.ProductProperty;
import com.example.assignment.model.query.ProductQueryParameters;
import com.example.assignment.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("/product")
	public DataDTO getProduct(@Validated ProductQueryParameters query) {
		List<Product> products = productRepository.search(query);
		List<ProductDTO> productsDto = new ArrayList<ProductDTO>();
		products.forEach(p -> {
			productsDto.add(
					new ProductDTO(p.getType(), getProperties(p.getProperties()), p.getPrice(), p.getStoreAddress()));
		});
		DataDTO result = new DataDTO();
		result.setData(productsDto);
		return result;
	}

	private String getProperties(ProductProperty properties) {
		if (properties instanceof ProductColorProperty) {
			return properties.getType() + ":" + ((ProductColorProperty) properties).getValue();
		} else {
			return properties.getType() + ":" + ((ProductGbLimitProperty) properties).getLimit();
		}
	}

}
