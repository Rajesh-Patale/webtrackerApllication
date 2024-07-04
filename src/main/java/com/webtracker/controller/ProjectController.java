package com.webtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.webtracker.entity.Project;
import com.webtracker.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("*")
public class ProjectController {
	
	
	   @Autowired
	    private ProjectService projectService;

	    @GetMapping
	    public List<Project> getAllProjects() {
	        return projectService.getAllProjects();
	    }

	    @GetMapping("/{id}")
	    public Project getProjectById(@PathVariable Long id) {
	        return projectService.getProjectById(id);
	    }

	    @PostMapping
	    public Project createProject(@RequestBody Project project) {
	        return projectService.createProject(project);
	    }

	    @PutMapping("/{id}")
	    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
	        return projectService.updateProject(id, projectDetails);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteProject(@PathVariable Long id) {
	        projectService.deleteProject(id);
	    }

}
