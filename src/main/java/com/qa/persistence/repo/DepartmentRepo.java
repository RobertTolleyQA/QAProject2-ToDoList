package com.qa.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.persistence.domain.DepartmentDomain;

public interface DepartmentRepo extends JpaRepository<DepartmentDomain, Long>{

}
