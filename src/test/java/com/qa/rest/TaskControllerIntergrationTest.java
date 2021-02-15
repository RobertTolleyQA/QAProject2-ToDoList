package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

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
import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskControllerIntergrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private TaskDTO mapToDTO(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}

	private final Long ID = 3L;

//	READ ALL

	@Test
	public void readAll() throws Exception {
		TaskDTO exRes1 = new TaskDTO(1L, "Java", "write java code for website CRUD", 450.99, 5);
		TaskDTO exRes2 = new TaskDTO(2L, "Html", "Create the look for the site", 55.2, 3);
		TaskDTO exRes3 = new TaskDTO(3L, "Managing", "Overlook the project", 0.0, 1);
		List<TaskDTO> expectedList = new ArrayList<>();
		expectedList.add(exRes1);
		expectedList.add(exRes2);
		expectedList.add(exRes3);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/tasks/readAll");

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedList));

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

//	READ ONE

	@Test
	public void readOne() throws Exception {

		TaskDTO expectedResult = new TaskDTO(3L, "Managing", "Overlook the project", 0.0, 1);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/tasks/readOne/" + ID);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

	}

//	CREATE

	@Test
	public void create() throws Exception {

		TaskDomain contentBody = new TaskDomain("run", "running", 5.5, 1, null);
		TaskDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(4L);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/tasks/create").contentType(MediaType.APPLICATION_JSON)
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

		TaskDomain contentBody = new TaskDomain(3L, "bob", "Bill", 5.5, 6, null);
		TaskDTO expectedResult = mapToDTO(contentBody);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/tasks/update/" + ID)
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
				"http://localhost:8080/tasks/delete/" + ID);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	
//	DELETE IF NOT EXISTS
	
	@Test
	public void deleteWrong() throws Exception {
		
		Long wrongID = 100L;

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "http://localhost:8080/tasks/delete/" + wrongID);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();

		this.mock.perform(mockRequest).andExpect(matchStatus);

	}
}
