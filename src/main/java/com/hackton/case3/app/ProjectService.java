package com.hackton.case3.app;

import com.hackton.case3.domain.Customer;
import com.hackton.case3.domain.Employee;
import com.hackton.case3.domain.Project;
import com.hackton.case3.domain.Team;
import com.hackton.case3.infrastructure.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private JpaProjectRepository projectRepository;

    @Autowired
    private JpaTeamRepository teamRepository;

    @Autowired JpaCustomerRepository customerRepository;

    public ProjectDto getProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();

        TeamDto team = null;
        if (project.getTeam() != null) {
            team = new TeamDto(
                    project.getTeam().getId(),
                    project.getTeam().getEmployees().stream()
                            .map(employee -> new EmployeeDto(
                                    employee.getUsername(), employee.getFirstname(), employee.getLastname()
                            )).toList()
            );
        }

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

        return new ProjectDto(project.getId(), project.getName(), team, customer, tasks);
    }

    public Long createProject(String name) {
        Project project = projectRepository.save(new Project(name));
        return project.getId();
    }

    public Long addCustomer(Long id, CustomerDto customerDto) {
        Project project = projectRepository.findById(id).orElseThrow();
        Customer customer = customerRepository.save(
                new Customer(customerDto.getFirstname(), customerDto.getLastname())
        );

        project.setCustomer(customer);
        customer.getProjects().add(project);

        customerRepository.save(customer);
        project = projectRepository.save(project);
        return project.getId();
    }

    public Long addTeam(Long id, Long teamId) {
        Project project = projectRepository.findById(id).orElseThrow();
        Team team = teamRepository.findById(teamId).orElseThrow();

        project.setTeam(team);
        team.getProjects().add(project);

        teamRepository.save(team);
        project = projectRepository.save(project);
        return project.getId();
    }
}
