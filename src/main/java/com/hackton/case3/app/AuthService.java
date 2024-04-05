package com.hackton.case3.app;

import com.hackton.case3.domain.Employee;
import com.hackton.case3.infrastructure.JwtTokenUtil;
import com.hackton.case3.infrastructure.dto.AuthRequest;
import com.hackton.case3.infrastructure.dto.JwtAuthResponse;
import com.hackton.case3.infrastructure.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthResponse signUp(SignUpRequest request) {

        Employee user = new Employee(
                request.getFirstname(),
                request.getLastname(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );

        userService.create(user);

        String jwt = jwtTokenUtil.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthResponse signIn(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        Employee user = (Employee) userService.loadUserByUsername(request.getUsername());

        String jwt = jwtTokenUtil.generateToken(user);
        return new JwtAuthResponse(jwt);
    }
}
