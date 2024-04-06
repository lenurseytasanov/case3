package com.hackton.case3.app;

import com.hackton.case3.domain.Employee;
import com.hackton.case3.domain.Project;
import com.hackton.case3.domain.Team;
import com.hackton.case3.infrastructure.dto.*;
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

    public TeamResponse getTeam(Long id) {
        List<EmployeeDto> employees = teamRepository.findById(id)
                .orElseThrow().getEmployees()
                .stream()
                .map(employee -> new EmployeeDto(employee.getUsername(), employee.getFirstname(), employee.getLastname()))
                .toList();
        List<ProjectResponse> projects = teamRepository.findById(id)
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

                    return new ProjectResponse(project.getId(), project.getName(), customer, tasks);
                        }
                )
                .toList();
        return new TeamResponse(id, employees, projects);
    }

    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(team -> getTeam(team.getId()))
                .toList();
    }
}
