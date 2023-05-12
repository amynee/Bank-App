package com.example.Bank.auth;

import com.example.Bank.role.Role;
import com.example.Bank.role.RoleRepository;
import com.example.Bank.role.RoleType;
import com.example.Bank.security.JwtService;
import com.example.Bank.user.User;
import com.example.Bank.user.UserRepository;
import com.example.Bank.user.UserRequest;
import com.example.Bank.user.UserService;
import com.example.Bank.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<RegisterRequest> validator;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        validator.validate(request);
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword()))
                .build();
        // set role
        var userRole = roleRepository.findByName(RoleType.ROLE_USER.name())
            .orElse(
                    Role.builder()
                        .name(RoleType.ROLE_USER.name())
                        .build()
            );

        if (userRole.getId() == null) {
            userRole = roleRepository.save(userRole);
        }
        var defaultUserRole = List.of(userRole);
        user.setRoles(defaultUserRole);
        var savedUser = userRepository.save(user);

        userRole.setUsers(new ArrayList<>(List.of(savedUser)));
        roleRepository.save(userRole);

        var jwtToken = jwtService.generateToken(savedUser);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
