package com.mycompany.codereviewplatform.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") 
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // HTTP POST: http://localhost:8080/api/projects/submit?userId=1
    
    @PostMapping("/submit")
    public ResponseEntity<?> submitProject(@RequestBody Project project, @RequestParam Long userId) {
        try {
            Project savedProject = projectService.saveProject(project, userId);
            return ResponseEntity.ok(savedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // HTTP GET: http://localhost:8080/api/projects
    
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // HTTP GET: http://localhost:8080/api/projects/user/1
    @GetMapping("/user/{userId}")
    public List<Project> getProjectsByUserId(@PathVariable Long userId) {
        return projectService.getProjectsByUserId(userId);
    }
}
