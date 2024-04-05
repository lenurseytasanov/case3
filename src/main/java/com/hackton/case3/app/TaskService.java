package com.hackton.case3.app;

import com.hackton.case3.domain.Employee;
import com.hackton.case3.domain.Project;
import com.hackton.case3.domain.Task;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.infrastructure.dto.TaskDto;
import com.hackton.case3.infrastructure.dto.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

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
        return taskRepository.save(task);
    }

    public Task changeState(Long id, State state) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setState(state);
        return taskRepository.save(task);
    }

    public Task changeStage(Long id, Stage stage) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setStage(stage);
        return taskRepository.save(task);
    }

    public Task addParent(Long id, Long parentTaskId) {
        Task task = taskRepository.findById(id).orElseThrow();
        Task parent = taskRepository.findById(parentTaskId).orElseThrow();

        task.setParent(parent);
        parent.getChildren().add(task);

        taskRepository.save(parent);
        return taskRepository.save(task);
    }

    public Task changeEstimation(Long id, OffsetDateTime startDate) {
        Task task = taskRepository.findById(id).orElseThrow();

        task.setStartDate(startDate);
        return taskRepository.save(task);
    }
}
