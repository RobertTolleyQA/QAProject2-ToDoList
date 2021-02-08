package com.qa.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.TaskDomain;

@Repository
public interface TaskRepo extends JpaRepository<TaskDomain, Long>{

}
