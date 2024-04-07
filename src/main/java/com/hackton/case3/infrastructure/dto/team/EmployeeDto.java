package com.hackton.case3.infrastructure.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class EmployeeDto {

    private String username;
    private String firstname;
    private String lastname;
}
