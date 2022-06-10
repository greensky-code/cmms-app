package com.cognato.cmms.controller;

import com.cognato.cmms.entity.User;
import com.cognato.cmms.jwt.JwtResponse;
import com.cognato.cmms.jwt.JwtUtils;
import com.cognato.cmms.model.LoginRequest;
import com.cognato.cmms.model.UserRequest;
import com.cognato.cmms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt,
          loginRequest.getUserName()));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserRequest userRequest) {
        userService.save(userRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
