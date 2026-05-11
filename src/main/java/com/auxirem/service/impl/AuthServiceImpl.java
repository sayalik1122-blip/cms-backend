package com.auxirem.service.impl;

import com.auxirem.model.Role;
import com.auxirem.model.Student;
import com.auxirem.model.User;
import com.auxirem.payload.request.LoginRequest;
import com.auxirem.payload.request.RegisterRequest;
import com.auxirem.payload.response.AuthResponse;
import com.auxirem.payload.response.StudentResponse;
import com.auxirem.payload.response.UserResponse;
import com.auxirem.repository.StudentRepository;
import com.auxirem.repository.UserRepository;
import com.auxirem.security.JwtUtils;
import com.auxirem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtils.generateToken(userDetails);

        // Fetch user details to return in response
        var admin = userRepository.findByEmail(request.getEmail());
        if (admin.isPresent()) {
            User user = admin.get();
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setInstitution(user.getInstitution());
            response.setRole(user.getRole());
            return AuthResponse.builder()
                    .token(token)
                    .user(response)
                    .role(user.getRole().name())
                    .build();
        }

        var studentOpt = studentRepository.findByEmail(request.getEmail());
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            StudentResponse response = new StudentResponse();
            response.setId(student.getId());
            response.setDisplayId(String.format("S%03d", student.getId()));
            response.setName(student.getName());
            response.setEmail(student.getEmail());
            response.setRole(student.getRole());
            response.setCourse(student.getCourse());
            response.setYear(student.getYear());
            response.setStatus(student.getStatus());
            return AuthResponse.builder()
                    .token(token)
                    .user(response)
                    .role(student.getRole().name())
                    .build();
        }

        throw new RuntimeException("User not found after authentication");
    }

    @Override
    public void registerAdmin(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .institution(request.getInstitution())
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }
}
