package com.hackton.case3.infrastructure.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class JwtAuthResponse {

    private String token;
}
