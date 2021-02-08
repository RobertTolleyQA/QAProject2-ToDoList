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

import com.qa.persistence.domain.DepartmentDomain;
import com.qa.persistence.dtos.DepartmentDTO;
import com.qa.persistence.repo.DepartmentRepo;

@Service
public class DepartmentServices {

	
	private DepartmentRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public DepartmentServices(DepartmentRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private DepartmentDTO mapToDTO(DepartmentDomain dept) {
		return this.mapper.map(dept, DepartmentDTO.class);
	}
	
//	GET
	
	@GetMapping("/read")
	public List<DepartmentDTO> readAll() {
		List<DepartmentDomain> dblist = this.repo.findAll();
		List<DepartmentDTO> resultList = dblist.stream().map(this::mapToDTO).collect(Collectors.toList());

		return resultList;
	}
	
//	GET

	@GetMapping("/readOne/{id}")
	public DepartmentDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());

	}

//	POST

	@PostMapping("/create")
	public DepartmentDTO create(DepartmentDomain dept) {
		return this.mapToDTO(this.repo.save(dept));

	}

//	PUT

	@PutMapping("/update")
	public DepartmentDTO update(Long id, DepartmentDomain newDetails) {
		this.repo.findById(id).orElseThrow();

		newDetails.setId(id);
		DepartmentDTO result = mapToDTO(this.repo.save(newDetails));

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
