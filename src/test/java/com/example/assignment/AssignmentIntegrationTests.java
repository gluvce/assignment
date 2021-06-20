package com.example.assignment;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentIntegrationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

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

		String response = mvc.perform(MockMvcRequestBuilders.get("/product")).andReturn().getResponse()
				.getContentAsString();
		DataDTOTest dataDTO = objectMapper.readValue(response, DataDTOTest.class);
		String expectedResponse = objectMapper.writeValueAsString(dataDTO);
		
		assertNotNull(dataDTO);
		assertNotNull(dataDTO.getData());
		assertNotEquals(dataDTO.getData().size(), 0);
		
		//check if model is changed
		JSONAssert.assertEquals(response, expectedResponse, false);
	}
}
