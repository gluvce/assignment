package com.example.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.assignment.model.Product;
import com.example.assignment.model.ProductType;
import com.example.assignment.model.query.ProductQueryParameters;
import com.example.assignment.model.query.ProductQueryProperty;
import com.example.assignment.repository.ProductRepository;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentUnitTests {

	@Autowired
	private ProductRepository repository; // mock the repository

//	proper way would be if we use separate csv file only for testing that will be read from test properties
//	@Value( "${csv.file}" )
//	private String fileName;

	// testing search method

	@Test
	void testSizeWithoutQuery() throws Exception {
		List<Product> productsFromCsv = repository.search(new ProductQueryParameters());
		assertEquals(productsFromCsv.size(), 100);
	}

	@Test
	void testSizeWithProductTypeParameter() throws Exception {
		ProductQueryParameters query = new ProductQueryParameters(ProductType.phone, null, null, null, null);
		List<Product> productsFromCsv = repository.search(query);
		assertEquals(productsFromCsv.size(), 42);
	}

	@Test
	void testSizeWithAllQueryParameters() throws Exception {
		ProductQueryParameters query = new ProductQueryParameters(ProductType.phone, new BigDecimal(380),
				new BigDecimal(380), "Stockholm", new ProductQueryProperty("brun", null, null));
		List<Product> productsFromCsv = repository.search(query);
		assertEquals(productsFromCsv.size(), 1);
	}

}
