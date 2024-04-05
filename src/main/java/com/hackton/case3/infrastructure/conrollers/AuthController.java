package com.hackton.case3.infrastructure.conrollers;

import com.hackton.case3.app.AuthService;
import com.hackton.case3.infrastructure.dto.AuthRequest;
import com.hackton.case3.infrastructure.dto.JwtAuthResponse;
import com.hackton.case3.infrastructure.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public JwtAuthResponse signUp(@RequestBody SignUpRequest request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthResponse signIn(@RequestBody AuthRequest request) {
        return authService.signIn(request);
    }

}
