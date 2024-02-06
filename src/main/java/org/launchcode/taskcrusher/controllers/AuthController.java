package org.launchcode.taskcrusher.controllers;

import jakarta.validation.Valid;
import org.launchcode.taskcrusher.models.ERole;
import org.launchcode.taskcrusher.models.Role;
import org.launchcode.taskcrusher.models.User;
import org.launchcode.taskcrusher.payload.request.LoginRequest;
import org.launchcode.taskcrusher.payload.request.SignupRequest;
import org.launchcode.taskcrusher.payload.response.JwtResponse;
import org.launchcode.taskcrusher.payload.response.MessageResponse;
import org.launchcode.taskcrusher.repository.RoleRepository;
import org.launchcode.taskcrusher.repository.UserRepository;
import org.launchcode.taskcrusher.security.jwt.JwtUtils;
import org.launchcode.taskcrusher.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use"));
        }

        //Create a new user's account
        //-----------------------------LIKELY NEED TO ADJUST ROLES TO ONLY PARENT AND KID---------------
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "parent":
                        Role parentRole = roleRepository.findByName(ERole.ROLE_PARENT_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(parentRole);
                        break;
                    case "kid":
                        Role kidRole = roleRepository.findByName(ERole.ROLE_KID_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(kidRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        //-----------------------------LIKELY NEED TO ADJUST ROLES TO ONLY PARENT AND KID---------------

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
