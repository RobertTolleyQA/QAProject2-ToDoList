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
	
	private Double estTime;
	
	private Integer estWorkers;

	public TaskDTO(Long id, @NotNull String name, String desc, Double estTime, Integer estWorkers) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.estTime = estTime;
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

	public Double getEstTime() {
		return estTime;
	}

	public void setEstTime(Double estTime) {
		this.estTime = estTime;
	}

	public Integer getEstWorkers() {
		return estWorkers;
	}

	public void setEstWorkers(Integer estWorkers) {
		this.estWorkers = estWorkers;
	}

}
