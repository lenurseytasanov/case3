package com.hackton.case3.app;

import com.hackton.case3.app.repository.JpaTeamRepository;
import com.hackton.case3.domain.Team;
import com.hackton.case3.infrastructure.dto.project.CustomerDto;
import com.hackton.case3.infrastructure.dto.project.TaskDto;
import com.hackton.case3.infrastructure.dto.team.EmployeeDto;
import com.hackton.case3.infrastructure.dto.team.ProjectDto;
import com.hackton.case3.infrastructure.dto.team.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private JpaTeamRepository teamRepository;

    public Team createTeam() {
        return teamRepository.save(new Team());
    }

    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElseThrow();
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
