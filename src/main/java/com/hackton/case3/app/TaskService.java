package com.hackton.case3.app;

import com.hackton.case3.app.repository.JpaEmployeeRepository;
import com.hackton.case3.app.repository.JpaProjectRepository;
import com.hackton.case3.app.repository.JpaTaskRepository;
import com.hackton.case3.domain.Employee;
import com.hackton.case3.domain.Project;
import com.hackton.case3.domain.Task;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.infrastructure.dto.project.CustomerDto;
import com.hackton.case3.infrastructure.dto.project.TeamDto;
import com.hackton.case3.infrastructure.dto.task.ProjectDto;
import com.hackton.case3.infrastructure.dto.task.TaskRequest;
import com.hackton.case3.infrastructure.dto.task.TaskResponse;
import com.hackton.case3.infrastructure.dto.team.EmployeeDto;
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

    public Task createTask(TaskRequest taskDto) {
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
        return task;
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task changeState(Long id, State state) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setState(state);
        task = taskRepository.save(task);
        return task;
    }

    public Task changeStage(Long id, Stage stage) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setStage(stage);
        task = taskRepository.save(task);
        return task;
    }

    public Task addParent(Long id, Long parentTaskId) {
        Task task = taskRepository.findById(id).orElseThrow();
        Task parent = taskRepository.findById(parentTaskId).orElseThrow();

        task.setParent(parent);
        parent.getChildren().add(task);

        taskRepository.save(parent);
        task = taskRepository.save(task);
        return task;
    }

    public Task changeEstimation(Long id, OffsetDateTime startDate) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setStartDate(startDate);
        task = taskRepository.save(task);
        return task;
    }
}
