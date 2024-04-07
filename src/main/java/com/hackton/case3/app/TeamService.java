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

    public Long createTeam() {
        Team team = teamRepository.save(new Team());
        return team.getId();
    }

    public TeamDto getTeam(Long id) {
        List<EmployeeDto> employees = teamRepository.findById(id)
                .orElseThrow().getEmployees()
                .stream()
                .map(employee -> new EmployeeDto(employee.getUsername(), employee.getFirstname(), employee.getLastname()))
                .toList();
        List<ProjectDto> projects = teamRepository.findById(id)
                .orElseThrow().getProjects()
                .stream()
                .map(project -> {
                    CustomerDto customer = null;
                    if (project.getCustomer() != null) {
                        customer = new CustomerDto(project.getCustomer().getFirstname(),
                                project.getCustomer().getLastname());
                    }
                    List<TaskDto> tasks = project.getTasks().stream()
                            .map(task -> new TaskDto(
                                    task.getId(), task.getText(),
                                    new EmployeeDto(task.getEmployee().getUsername(),
                                            task.getEmployee().getFirstname(), task.getEmployee().getLastname()
                                    ), task.getState(), task.getStage(), task.getPriority(), task.getType(), task.getStartDate()))
                            .toList();

                    return new ProjectDto(project.getId(), project.getName(), customer, tasks);
                        }
                )
                .toList();
        return new TeamDto(id, employees, projects);
    }

    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(team -> getTeam(team.getId()))
                .toList();
    }
}
