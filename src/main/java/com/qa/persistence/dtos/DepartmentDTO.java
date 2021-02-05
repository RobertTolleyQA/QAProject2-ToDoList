package com.qa.persistence.dtos;

import java.util.List;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DepartmentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
	private String name;
	
	private List<TaskDTO> taskList;

	public DepartmentDTO(Long id, String name, List<TaskDTO> taskList) {
		super();
		this.id = id;
		this.name = name;
		this.taskList = taskList;
	}

	public DepartmentDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskDTO> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskDTO> taskList) {
		this.taskList = taskList;
	}

}
