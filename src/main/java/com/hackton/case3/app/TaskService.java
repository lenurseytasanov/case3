package com.hackton.case3.app;

import com.hackton.case3.domain.Employee;
import com.hackton.case3.domain.Project;
import com.hackton.case3.domain.Task;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.infrastructure.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private JpaTaskRepository taskRepository;

    @Autowired
    private JpaEmployeeRepository employeeRepository;

    @Autowired
    private JpaProjectRepository projectRepository;

    public Long createTask(TaskRequest taskDto) {
        Employee employee = employeeRepository.findById(taskDto.getEmployeeId()).orElseThrow();
        Project project = projectRepository.findById(taskDto.getProjectId()).orElseThrow();

        Task task = taskRepository.save(new Task(
                taskDto.getText(), employee, project, taskDto.getState(), taskDto.getStage(),
                taskDto.getPriority(), taskDto.getType()
        ));

        employee.getTasks().add(task);
        project.getTasks().add(task);

        employeeRepository.save(employee);
        projectRepository.save(project);
        task = taskRepository.save(task);
        return task.getId();
    }

    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();

        TeamDto teamDto = null;
        if (task.getProject().getName() != null) {
            teamDto = new TeamDto(
                    task.getProject().getTeam().getId(),
                    task.getProject().getTeam().getEmployees().stream()
                            .map(employee -> new EmployeeDto(employee.getUsername(), employee.getFirstname(), employee.getLastname()))
                            .toList()

            );
        }

        CustomerDto customerDto = null;
        if (task.getProject().getCustomer() != null) {
            customerDto = new CustomerDto(task.getProject().getCustomer().getFirstname(),
                    task.getProject().getCustomer().getLastname());
        }

        ProjectTaskResponse projectTaskResponse = new ProjectTaskResponse(
                task.getProject().getId(), task.getProject().getName(), teamDto, customerDto
        );


        return new TaskResponse(task.getId(), task.getText(),
                new EmployeeDto(task.getEmployee().getUsername(), task.getEmployee().getFirstname(), task.getEmployee().getLastname()),
                projectTaskResponse, task.getState(), task.getStage(), task.getPriority(), task.getType(), task.getStartDate());
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(task -> getTask(task.getId())).toList();
    }

    public Long changeState(Long id, State state) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setState(state);
        task = taskRepository.save(task);
        return task.getId();
    }

    public Long changeStage(Long id, Stage stage) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setStage(stage);
        task = taskRepository.save(task);
        return task.getId();
    }

    public Long addParent(Long id, Long parentTaskId) {
        Task task = taskRepository.findById(id).orElseThrow();
        Task parent = taskRepository.findById(parentTaskId).orElseThrow();

        task.setParent(parent);
        parent.getChildren().add(task);

        taskRepository.save(parent);
        task = taskRepository.save(task);
        return task.getId();
    }

    public Long changeEstimation(Long id, OffsetDateTime startDate) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setStartDate(startDate);
        task = taskRepository.save(task);
        return task.getId();
    }
}
