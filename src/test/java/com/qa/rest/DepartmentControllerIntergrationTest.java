package com.qa.rest;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.DepartmentDomain;

import com.qa.persistence.dtos.DepartmentDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class DepartmentControllerIntergrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private DepartmentDTO mapToDTO(DepartmentDomain model) {
		return this.mapper.map(model, DepartmentDTO.class);
	}
	
	private final Long ID = 3L;
	
//	CREATE
	
	@Test
	public void create() throws Exception {

		DepartmentDomain contentBody = new DepartmentDomain(4L, "temp", null);
		DepartmentDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(4L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/dept/create").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		System.out.println(matchStatus);
		System.out.println(matchContent);
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

	}
	
//	UPDATE
	
	@Test
	public void update() throws Exception {

		DepartmentDomain contentBody = new DepartmentDomain(3L, "temp", null);
		DepartmentDTO expectedResult = mapToDTO(contentBody);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/dept/update/" + ID)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
//	DELETE

	@Test
	public void delete() throws Exception {

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/dept/delete/" + ID);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	
//	DELETE IF NOT EXISTS
	
	@Test
	public void deleteWrong() throws Exception {
		
		Long wrongID = 100L;

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "http://localhost:8080/dept/delete/" + wrongID);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();

		this.mock.perform(mockRequest).andExpect(matchStatus);

	}

}
