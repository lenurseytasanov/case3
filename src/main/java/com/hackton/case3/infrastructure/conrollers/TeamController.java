package com.hackton.case3.infrastructure.conrollers;

import com.hackton.case3.app.TeamService;
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
    public ResponseEntity<?> createTeam() {
        return ResponseEntity.ok(teamService.createTeam());
    }

    @GetMapping(path = "/{id}/participants")
    public ResponseEntity<?> getParticipants(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getParticipants(id));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Long>> getAllTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }
}
