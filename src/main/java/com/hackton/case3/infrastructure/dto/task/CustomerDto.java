package com.hackton.case3.infrastructure.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String firstname;

    private String lastname;
}
