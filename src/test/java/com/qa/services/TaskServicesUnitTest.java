package com.qa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.persistence.repo.TaskRepo;

@SpringBootTest
public class TaskServicesUnitTest {

	@MockBean // @Mock
	private ModelMapper MockMapper;
	@MockBean // @Mock
	private TaskRepo mockRepo;

	@Autowired // @injectMocks
	private TaskServices service;
	
//	CREATE
	
	@Test
	public void create() {

		TaskDomain testTask = new TaskDomain(4L, "run", "running", 1.1, 2, null);
		TaskDTO testDTO = new TaskDTO(4L, "run", "running", 1.1, 2);

		Mockito.when(this.mockRepo.save(Mockito.any(TaskDomain.class))).thenReturn(testTask);
		Mockito.when(this.MockMapper.map(testTask, TaskDTO.class)).thenReturn(testDTO);


		TaskDTO result = this.service.create(testTask);


		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison()
		.isEqualTo(testDTO);
		
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
		Mockito.verify(this.MockMapper, Mockito.times(1)).map(testTask, TaskDTO.class);
	}
	
//	READ ALL
	
	@Test
	public void readAll() {
		TaskDomain testTask = new TaskDomain(1L, "run", "running", 10.5, 1, null);
		List<TaskDomain> testList =  new ArrayList<>();
		testList.add(testTask);
		
		TaskDTO testDTO = new TaskDTO(1L, "run", "running", 10.5, 1);
		List<TaskDTO> testDTOList = new ArrayList<>();
		testDTOList.add(testDTO);

		Mockito.when(this.mockRepo.findAll() ).thenReturn(testList);
		Mockito.when(this.MockMapper.map(testTask, TaskDTO.class)).thenReturn(testDTO);


		List<TaskDTO> result = this.service.readAll();

		Assertions.assertThat(result).usingRecursiveComparison()
		.isEqualTo(testDTOList);

	}
	
	@Test
	public void readOne() {
		TaskDomain testTask = new TaskDomain(1L, "run", "running", 1.1, 2, null);

		Mockito.when(this.mockRepo.findById(testTask.getId())).thenReturn(Optional.of(testTask));


		TaskDTO result = this.service.readOne(1L);

		Assertions.assertThat(result).usingRecursiveComparison()
		.isEqualTo(this.MockMapper.map(testTask, TaskDTO.class));
		
		Mockito.verify(this.mockRepo, Mockito.times(1)).findById(1L);
	}
	
}
