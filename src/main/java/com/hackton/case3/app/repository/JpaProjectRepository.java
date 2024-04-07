package com.hackton.case3.app.repository;

import com.hackton.case3.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProjectRepository extends JpaRepository<Project, Long> {
}
