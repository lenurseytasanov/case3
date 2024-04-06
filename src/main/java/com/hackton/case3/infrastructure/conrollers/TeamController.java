package com.hackton.case3.infrastructure.conrollers;

import com.hackton.case3.app.TeamService;
import com.hackton.case3.infrastructure.dto.TeamDto;
import com.hackton.case3.infrastructure.dto.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping(path = "/new")
    public ResponseEntity<Long> createTeam() {
        return ResponseEntity.ok(teamService.createTeam());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TeamResponse  > getTeam(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeam(id));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
}
