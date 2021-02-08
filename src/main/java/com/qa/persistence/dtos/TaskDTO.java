package com.qa.persistence.dtos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class TaskDTO {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String desc;
	
//	Date date = new Date();
//	private String currentDate = date.toString();
	
//	public String getDate() {
//		return currentDate;
//	}
	
	private Double estCost;
	
	private Integer estWorkers;

	public TaskDTO(Long id, String name, String desc, Double estCost, Integer estWorkers) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.estCost = estCost;
		this.estWorkers = estWorkers;
	}
	
	

	public TaskDTO(String name, String desc, Double estCost, Integer estWorkers) {
		super();
		this.name = name;
		this.desc = desc;
		this.estCost = estCost;
		this.estWorkers = estWorkers;
	}



	public TaskDTO() {
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
	
	
	
	


	
	
	
}

