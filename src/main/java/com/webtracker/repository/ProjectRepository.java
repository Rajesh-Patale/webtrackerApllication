package com.webtracker.repository;

import com.webtracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByLoginTimeAsc();
    List<Project> findAllByEmail(String email);
}
