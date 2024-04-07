package com.hackton.case3.infrastructure.dto.task;

import com.hackton.case3.domain.enums.Priority;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.domain.enums.Type;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TaskRequest {

    private String text;

    private Long employeeId;

    private Long projectId;

    private State state;

    private Stage stage;

    private Priority priority;

    private Type type;
}
