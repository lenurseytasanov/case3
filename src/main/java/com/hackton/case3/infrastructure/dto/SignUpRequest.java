package com.hackton.case3.infrastructure.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignUpRequest {

    private String username;

    private String password;

    private String firstName;

    private String lastName;
}
