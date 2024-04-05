package com.hackton.case3.infrastructure.dto;

import lombok.Data;
import lombok.Getter;

@Data @Getter
public class AuthRequest {

    private String username;

    private String password;
}
