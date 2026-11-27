package com.vcube.healthcare.controller;
import com.vcube.healthcare.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.vcube.healthcare.repository.UserRepository;
import com.vcube.healthcare.dto.AuthResponseDTO;
import com.vcube.healthcare.dto.LoginRequestDTO;
import com.vcube.healthcare.security.CustomUserDetailsService;
import com.vcube.healthcare.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
  // LOGIN API
@PostMapping("/login")
public ResponseEntity<AuthResponseDTO> login(
        @RequestBody LoginRequestDTO request) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    UserDetails userDetails =
            userDetailsService.loadUserByUsername(
                request.getUsername()
            );

    String token = jwtUtil.generateToken(userDetails);

    // Get actual user ID from database
    User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    return ResponseEntity.ok(
        new AuthResponseDTO(
            token,
            "Login successful",
            user.getUserId()
        )
    );
}

    // PROFILE API
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(
            Authentication authentication) {

        return ResponseEntity.ok(
            "Welcome " + authentication.getName()
        );
    }
}
