package com.example.assignment.repository;

import com.example.assignment.model.Product;
import com.example.assignment.model.ProductType;
import com.example.assignment.model.query.ProductQueryParameters;
import com.example.assignment.model.query.ProductQueryProperty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductRepositoryTest {

    ProductRepository productRepository;

    @BeforeEach
    void load(){
        Resource file = new ClassPathResource("testData.csv");
        productRepository = new ProductRepository(file);
    }

    @Test
    void shouldReturnAllWhenNoQueryParaetersSend(){
        // given
        ProductQueryParameters productQueryParameters = new ProductQueryParameters();
        // when
        List<Product> result = productRepository.search(productQueryParameters);
        // then
        assertNotNull(result);
        assertEquals(result.size(), 11);
    }

    @Test
    void shouldCheckAllQueryParametersInSearch() {
        // given
        ProductQueryParameters productQueryParameters = new ProductQueryParameters();
        productQueryParameters.setType(ProductType.phone);
        productQueryParameters.setCity("Stockholm");
        productQueryParameters.setMin_price(new BigDecimal(380));
        productQueryParameters.setMax_price(new BigDecimal(380));
        // when
        List<Product> result = productRepository.search(productQueryParameters);

        // then
        assertNotNull(result);
        assertEquals(result.size(), 1);
    }
    
	@Test
	void testSizeWithoutQuery() throws Exception {
		List<Product> productsFromCsv = productRepository.search(new ProductQueryParameters());
		assertEquals(productsFromCsv.size(), 11);
	}

	@Test
	void testSizeWithProductTypeParameter() throws Exception {
		ProductQueryParameters query = new ProductQueryParameters(ProductType.phone, null, null, null, null);
		List<Product> productsFromCsv = productRepository.search(query);
		assertEquals(productsFromCsv.size(), 7);
	}

	@Test
	void testSizeWithAllQueryParameters() throws Exception {
		ProductQueryParameters query = new ProductQueryParameters(ProductType.phone, new BigDecimal(380),
				new BigDecimal(380), "Stockholm", new ProductQueryProperty("brun", null, null));
		List<Product> productsFromCsv = productRepository.search(query);
		assertEquals(productsFromCsv.size(), 1);
	}

}
