package br.com.planningtravel.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.planningtravel.enums.RoleName;
import br.com.planningtravel.exception.AppException;
import br.com.planningtravel.model.Role;
import br.com.planningtravel.model.User;
import br.com.planningtravel.payload.ApiResponse;
import br.com.planningtravel.payload.JwtAuthenticationResponse;
import br.com.planningtravel.payload.LoginRequest;
import br.com.planningtravel.payload.SignUpRequest;
import br.com.planningtravel.repository.RoleRepository;
import br.com.planningtravel.repository.UserRepository;
import br.com.planningtravel.response.Response;
import br.com.planningtravel.security.JwtTokenProvider;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<Response<?>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(Response.success(new JwtAuthenticationResponse(jwt)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
        	return ResponseEntity.badRequest().body(new ApiResponse(false, "Username já foi cadastrado!"));
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        	return ResponseEntity.badRequest().body(new ApiResponse(false, "E-mail já está em uso!"));
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("Role do usuário não adicionada."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(Response.success(result));
    }

}
