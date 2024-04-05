package com.hackton.case3.app;

import com.hackton.case3.domain.Employee;
import com.hackton.case3.domain.Project;
import com.hackton.case3.domain.Team;
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

    public List<Employee> getParticipants(Long id) {
        return teamRepository.findByTeamId(id);
    }

    public List<Long> getTeams() {
        return teamRepository.findAll().stream().map(Team::getId).toList();
    }
}
