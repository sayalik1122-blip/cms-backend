package com.auxirem.security;

import com.auxirem.model.Student;
import com.auxirem.model.User;
import com.auxirem.repository.StudentRepository;
import com.auxirem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Check Admin Users first
        Optional<User> admin = userRepository.findByEmail(email);
        if (admin.isPresent()) {
            User user = admin.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
            );
        }

        // Check Students
        Optional<Student> studentOpt = studentRepository.findByEmail(email);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    student.getEmail(),
                    student.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + student.getRole().name()))
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
