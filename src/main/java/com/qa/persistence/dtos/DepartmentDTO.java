package com.qa.persistence.dtos;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.qa.persistence.domain.TaskDomain;

public class DepartmentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "myDepartment", fetch= FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<TaskDomain> taskList;

	public DepartmentDTO(Long id, String name, List<TaskDomain> taskList) {
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

	public List<TaskDomain> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskDomain> taskList) {
		this.taskList = taskList;
	}

}
