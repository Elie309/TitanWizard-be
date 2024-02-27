package com.elie309.ecommerce.Controllers.AuthController;

import com.elie309.ecommerce.Models.AccountsModels.Account;
import com.elie309.ecommerce.Repository.Auth.AuthRepository;
import com.elie309.ecommerce.Security.Auth.Models.AuthResponse;
import com.elie309.ecommerce.Security.Auth.Models.LoginRequest;
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

        if(!Account.isValid(account)){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Firstname, lastname, email & passord are required. Password should be at least of 8 charaters");
        }
        return new ResponseEntity<>(authRepository.register(account), HttpStatus.CREATED);
    }
}
