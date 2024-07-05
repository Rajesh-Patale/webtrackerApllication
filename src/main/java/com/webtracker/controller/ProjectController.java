package com.webtracker.controller;

import com.webtracker.entity.Project;
import com.webtracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("*")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project task) {
        return ResponseEntity.ok(projectService.createProject(task));
    }

    @GetMapping("/getProjectByEmail/{email}")
    public ResponseEntity<List<Project>> getAllProjectsByEmail(@RequestParam String email) {
        return ResponseEntity.ok(projectService.getAllProjectByEmail(email));
    }

    @GetMapping("/getAllProject")
    public ResponseEntity<List<Project>> getAllProject() {
        return ResponseEntity.ok(projectService.getAllProject());
    }
}
