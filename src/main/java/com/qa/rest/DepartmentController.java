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

import com.qa.persistence.domain.DepartmentDomain;
import com.qa.persistence.dtos.DepartmentDTO;
import com.qa.services.DepartmentServices;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
	
	private DepartmentServices service;
	
	@Autowired
	public DepartmentController(DepartmentServices service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<DepartmentDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/readOne/{id}")
	public ResponseEntity<DepartmentDTO> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));

	}

	@PostMapping("/create")
	public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDomain dept) {
		return new ResponseEntity<DepartmentDTO>(this.service.create(dept), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<DepartmentDTO> update(@PathVariable("id") Long id, @RequestBody DepartmentDomain dept) {
		return new ResponseEntity<DepartmentDTO>(this.service.update(id, dept), HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
