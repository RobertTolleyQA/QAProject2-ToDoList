package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.persistence.repo.TaskRepo;

@Service
public class TaskServices {
	
	private TaskRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public TaskServices(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private TaskDTO mapToDTO(TaskDomain task) {
		return this.mapper.map(task, TaskDTO.class);
	}
	
//	GET
	
	@GetMapping("/read")
	public List<TaskDTO> readAll() {
		List<TaskDomain> dblist = this.repo.findAll();
		List<TaskDTO> resultList = dblist.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		return resultList;
	}
	
//	GET

	@GetMapping("/readOne/{id}")
	public TaskDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
		
	}
	
//	POST

	@PostMapping("/create")
	public TaskDTO create(TaskDomain task) {
		return this.mapToDTO(this.repo.save(task));

	}
	
//	PUT

	@PutMapping("/update")
	public TaskDTO update(Long id, TaskDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		
		newDetails.setId(id);
		TaskDTO result = mapToDTO(this.repo.save(newDetails));
		
		return result;

	}
	
//	DELETE

	@DeleteMapping("/delete/{id}")
	public boolean delete(Long id) {
		
		
		try {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);

		return !exists;
		
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	

}
