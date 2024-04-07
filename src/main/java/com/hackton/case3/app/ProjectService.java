package com.hackton.case3.app;

import com.hackton.case3.app.repository.JpaCustomerRepository;
import com.hackton.case3.app.repository.JpaProjectRepository;
import com.hackton.case3.app.repository.JpaTeamRepository;
import com.hackton.case3.domain.Customer;
import com.hackton.case3.domain.Project;
import com.hackton.case3.domain.Team;
import com.hackton.case3.infrastructure.dto.project.CustomerDto;
import com.hackton.case3.infrastructure.dto.project.ProjectDto;
import com.hackton.case3.infrastructure.dto.project.TaskDto;
import com.hackton.case3.infrastructure.dto.project.TeamDto;
import com.hackton.case3.infrastructure.dto.team.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private JpaProjectRepository projectRepository;

    @Autowired
    private JpaTeamRepository teamRepository;

    @Autowired
    JpaCustomerRepository customerRepository;

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }

    public Project createProject(String name) {
        return projectRepository.save(new Project(name));
    }

    public Project addCustomer(Long id, Customer customer1) {
        Project project = projectRepository.findById(id).orElseThrow();
        Customer customer = customerRepository.save(
                new Customer(customer1.getFirstname(), customer1.getLastname())
        );

        project.setCustomer(customer);
        customer.getProjects().add(project);

        customerRepository.save(customer);
        project = projectRepository.save(project);
        return project;
    }

    public Project addTeam(Long id, Team team1) {
        Project project = projectRepository.findById(id).orElseThrow();
        Team team = teamRepository.findById(team1.getId()).orElseThrow();

        project.setTeam(team);
        team.getProjects().add(project);

        teamRepository.save(team);
        project = projectRepository.save(project);
        return project;
    }
}
