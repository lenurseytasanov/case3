package com.hackton.case3.infrastructure.dto;

import com.hackton.case3.domain.enums.Priority;
import com.hackton.case3.domain.enums.Stage;
import com.hackton.case3.domain.enums.State;
import com.hackton.case3.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTaskResponse {

    private Long id;
    private String name;
    private TeamDto team;
    private CustomerDto customer;
}
