package com.hackton.case3.infrastructure.conrollers;

import com.hackton.case3.app.AuthService;
import com.hackton.case3.domain.Employee;
import com.hackton.case3.infrastructure.dto.auth.AuthRequest;
import com.hackton.case3.app.JwtDto;
import com.hackton.case3.infrastructure.dto.auth.SignUpRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    public JwtDto signUp(@RequestBody SignUpRequest request) {
        Employee employee = modelMapper.map(request, Employee.class);
        return authService.signUp(employee);
    }

    @PostMapping("/login")
    public JwtDto signIn(@RequestBody AuthRequest request) {
        Employee employee = modelMapper.map(request, Employee.class);
        return authService.signIn(employee);
    }

}
