package com.hackton.case3.infrastructure.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private Long id;
    private List<EmployeeDto> employees;
    private List<ProjectDto> projects;
}
