package com.hackton.case3.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    private Long id;
    private List<EmployeeDto> employees;
    private List<ProjectResponse> projects;
}
