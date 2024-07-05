package com.webtracker.service;

import com.webtracker.entity.Project;
import com.webtracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository taskRepository;

    public Project createProject(Project task) {
        return taskRepository.save(task);
    }

    public List<Project> getAllProject() {
        return taskRepository.findAllByOrderByLoginTimeAsc();
    }

    public List<Project> getAllProjectByEmail(String email){
        return  taskRepository.findAllByEmail(email);
    }
}
