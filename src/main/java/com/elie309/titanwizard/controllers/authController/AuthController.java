package com.elie309.titanwizard.controllers.authController;

import com.elie309.titanwizard.models.accountsModels.Account;
import com.elie309.titanwizard.repository.authRepository.AuthRepository;
import com.elie309.titanwizard.security.auth.Models.AuthResponse;
import com.elie309.titanwizard.security.auth.Models.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthRepository authRepository;

    public AuthController(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @GetMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        if(!LoginRequest.isValid(loginRequest)){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Bad Credentials");
        }
        return ResponseEntity.ok(authRepository.login(loginRequest));
    }

    @PostMapping
    public ResponseEntity<AuthResponse> register(@RequestBody Account account){

        if(account.isNotValid()){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "First name, last name and email are required.");
        }

        if(!account.isValidPassword()){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Password must be at least 8 characters long.");
        }

        return new ResponseEntity<>(authRepository.register(account), HttpStatus.CREATED);
    }
}
