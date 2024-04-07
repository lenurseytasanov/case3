package com.hackton.case3.infrastructure.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Long id;
    private String name;
    private TeamDto team;
    private CustomerDto customer;
    private List<TaskDto> tasks;
}
