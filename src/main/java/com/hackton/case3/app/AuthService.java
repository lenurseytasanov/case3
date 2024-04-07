package com.hackton.case3.app;

import com.hackton.case3.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public JwtDto signUp(Employee employee) {

        Employee user = new Employee(
                employee.getFirstname(),
                employee.getLastname(),
                employee.getUsername(),
                passwordEncoder.encode(employee.getPassword())
        );

        userService.create(user);

        String jwt = jwtService.generateToken(user);
        return new JwtDto(jwt);
    }

    public JwtDto signIn(Employee employee) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                employee.getUsername(),
                employee.getPassword()
        ));

        Employee user = (Employee) userService.loadUserByUsername(employee.getUsername());

        String jwt = jwtService.generateToken(user);
        return new JwtDto(jwt);
    }
}
