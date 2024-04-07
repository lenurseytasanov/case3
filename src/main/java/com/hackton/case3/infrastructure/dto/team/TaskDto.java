package com.hackton.case3.infrastructure.dto.team;

import com.hackton.case3.domain.enums.Priority;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String text;

    private EmployeeDto employee;

    private State state;

    private Stage stage;

    private Priority priority;

    private Type type;

    private OffsetDateTime startDate;
}
