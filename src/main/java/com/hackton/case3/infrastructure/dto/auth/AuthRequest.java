package com.hackton.case3.infrastructure.dto.auth;

import lombok.Data;
import lombok.Getter;

@Data @Getter
public class AuthRequest {

    private String username;

    private String password;
}
