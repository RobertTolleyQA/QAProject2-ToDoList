package com.qa.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.DepartmentDomain;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentDomain, Long>{

}
