package com.example.assignment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.assignment.model.Product;
import com.example.assignment.model.ProductColorProperty;
import com.example.assignment.model.ProductType;
import com.example.assignment.model.query.ProductQueryParameters;
import com.example.assignment.model.query.ProductQueryProperty;
import com.example.assignment.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository repository; // mock the repository

	@Test
	void testOKStatus() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/product")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testJSONMediaType() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/product"))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testExpectedContent() throws Exception {
		// phone,color:grön,277.00,"Blake gränden, Karlskrona"
		List<Product> mockedList = new ArrayList<Product>();
		Product mockedProduct = new Product(ProductType.phone, new ProductColorProperty("grön"), new BigDecimal(277),
				"Blake gränden, Karlskrona");
		mockedList.add(mockedProduct);
		ProductQueryParameters query = new ProductQueryParameters(ProductType.phone, new BigDecimal(277),
				new BigDecimal(277), "Karlskrona", new ProductQueryProperty("grön", null, null));
//		List<Product> result = Mockito.doReturn(mockedList).when(repository).search(query);
		List<Product> result = repository.search(query);

		final String expectedResponseContent = objectMapper.writeValueAsString(mockedList);

		mvc.perform(MockMvcRequestBuilders.get("/product"))
				.andExpect(MockMvcResultMatchers.content().json(expectedResponseContent));
	}
}
