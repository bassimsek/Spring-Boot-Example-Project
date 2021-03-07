package com.example.demo.controller;


import com.example.demo.POJOs.UsernameAndPasswordAuthenticationRequest;
import com.example.demo.dto.UserDTO;
import com.example.demo.jwt.JwtConfig;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ApplicationUserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/customer/api/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;
    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;


    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    ApplicationUserService applicationUserService,
                                    UserRepository userRepository,
                                    JwtConfig jwtConfig,
                                    SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
        this.userRepository = userRepository;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }



    @PostMapping(path = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(applicationUserService.save(user));
    }



    @PostMapping(path = "/authenticate", produces ="application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = applicationUserService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities",  userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        Integer user_id = userRepository.findByUsername(authenticationRequest.getUsername()).getId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", user_id);


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(jsonObject.toString());
    }



    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

}
