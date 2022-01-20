package com.example.springbootblogrest.controller;

import com.example.springbootblogrest.dto.JWTAuthResponse;
import com.example.springbootblogrest.dto.LoginDto;
import com.example.springbootblogrest.dto.SignUpDto;
import com.example.springbootblogrest.entity.Role;
import com.example.springbootblogrest.entity.User;
import com.example.springbootblogrest.exception.ResourceNotFoundException;
import com.example.springbootblogrest.repository.RoleRepository;
import com.example.springbootblogrest.repository.UserRepository;
import com.example.springbootblogrest.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.Collections;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Api(value = "Authorization Controller exposes signing in and signup REST Api's")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @ApiOperation(value = "Rest api to signin a user to Blog App")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @ApiOperation(value = "Rest api to register a user to Blog App")
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {
        if(Boolean.TRUE.equals(userRepository.existsByUsername(signUpDto.getUsername()))) {
            return new ResponseEntity<>("Username is already taken", BAD_REQUEST);
        }

        if(Boolean.TRUE.equals(userRepository.existsByEmail(signUpDto.getEmail()))) {
            return new ResponseEntity<>("Email already exists", BAD_REQUEST);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Optional<Role> roles = roleRepository.findByName("ROLE_ADMIN");
        if(roles.isEmpty()) {
            throw new ResourceNotFoundException("role","role",1L);
        }
        user.setRoles(Collections.singleton(roles.get()));
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", OK);
    }
}
