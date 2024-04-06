package com.hackton.case3.infrastructure.conrollers;

import com.hackton.case3.app.TaskService;
import com.hackton.case3.domain.Task;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.infrastructure.dto.TaskDto;
import com.hackton.case3.infrastructure.dto.TaskRequest;
import com.hackton.case3.infrastructure.dto.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<TaskResponse>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Long> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping(path = "/{id}/state")
    public ResponseEntity<Long> changeState(@PathVariable Long id, @RequestBody State state) {
        return ResponseEntity.ok(taskService.changeState(id, state));
    }

    @PutMapping(path = "/{id}/stage")
    public ResponseEntity<Long> changeStage(@PathVariable Long id, @RequestBody Stage stage) {
        return ResponseEntity.ok(taskService.changeStage(id, stage));
    }

    @PutMapping(path = "/{id}/parent")
    public ResponseEntity<Long> addParent(@PathVariable Long id, @RequestBody Long parentTaskId) {
        return ResponseEntity.ok(taskService.addParent(id, parentTaskId));
    }

    @PutMapping(path = "/{id}/estimate")
    public ResponseEntity<Long> changeEstimation(@PathVariable Long id, @RequestBody OffsetDateTime startDate) {
        return ResponseEntity.ok(taskService.changeEstimation(id, startDate));
    }
}
