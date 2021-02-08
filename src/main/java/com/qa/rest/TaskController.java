package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.services.TaskServices;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	private TaskServices service;

	
	@Autowired
	public TaskController(TaskServices service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/readOne/{id}")
	public ResponseEntity<TaskDTO> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));

	}

	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDomain task) {
		return new ResponseEntity<TaskDTO>(this.service.create(task), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable("id") Long id, @RequestBody TaskDomain task) {
		return new ResponseEntity<TaskDTO>(this.service.update(id, task), HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
