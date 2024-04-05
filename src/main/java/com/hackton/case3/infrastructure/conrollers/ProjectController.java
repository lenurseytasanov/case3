package com.hackton.case3.infrastructure.conrollers;

import com.hackton.case3.app.ProjectService;
import com.hackton.case3.app.TeamService;
import com.hackton.case3.domain.Project;
import com.hackton.case3.infrastructure.dto.CustomerDto;
import com.hackton.case3.infrastructure.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Long> createProject(@RequestBody String name) {
        return ResponseEntity.ok(projectService.createProject(name));
    }

    @PutMapping(path = "/{id}/customer")
    public ResponseEntity<Long> addCustomer(@PathVariable Long id,
                                         @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(projectService.addCustomer(id, customerDto));
    }

    @PutMapping(path = "/{id}/team")
    public ResponseEntity<Long> addTeam(@PathVariable Long id,
                                     @RequestBody Long teamId) {
        return ResponseEntity.ok(projectService.addTeam(id, teamId));
    }
}
