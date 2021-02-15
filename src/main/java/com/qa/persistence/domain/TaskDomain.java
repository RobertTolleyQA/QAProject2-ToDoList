package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class TaskDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String desc;
	
	private Double estCost;
	
	private Integer estWorkers;
	
	@ManyToOne
	private DepartmentDomain myDepartment;




	public TaskDomain(Long id, String name, String desc, Double estCost, Integer estWorkers,
			DepartmentDomain myDepartment) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.estCost = estCost;
		this.estWorkers = estWorkers;
		this.myDepartment = myDepartment;
	}
	
	

	public TaskDomain(String name, String desc, Double estCost, Integer estWorkers, DepartmentDomain myDepartment) {
		super();
		this.name = name;
		this.desc = desc;
		this.estCost = estCost;
		this.estWorkers = estWorkers;
		this.myDepartment = myDepartment;
	}
	
	



	public TaskDomain() {
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getEstCost() {
		return estCost;
	}

	public void setEstCost(Double estCost) {
		this.estCost = estCost;
	}

	public Integer getEstWorkers() {
		return estWorkers;
	}

	public void setEstWorkers(Integer estWorkers) {
		this.estWorkers = estWorkers;
	}

	public DepartmentDomain getMyDepartment() {
		return myDepartment;
	}

	public void setMyDepartment(DepartmentDomain myDepartment) {
		this.myDepartment = myDepartment;
	}
	
	
	
	
	

}
