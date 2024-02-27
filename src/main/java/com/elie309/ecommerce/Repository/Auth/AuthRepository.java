package com.elie309.ecommerce.Repository.Auth;

import com.elie309.ecommerce.Models.AccountsModels.Account;
import com.elie309.ecommerce.Repository.AccountRepository.AccountRepository;
import com.elie309.ecommerce.Security.Auth.Models.AuthResponse;
import com.elie309.ecommerce.Security.Auth.Models.LoginRequest;
import com.elie309.ecommerce.Security.Auth.Models.UserInfoUserDetails;
import com.elie309.ecommerce.Security.JWT.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthRepository {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthRepository(AccountRepository accountRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(Account account){

        account.setAccountPassword(passwordEncoder.encode(account.getAccountPassword()));
        //It will throw an error if something happens
        Account updatedAccount = accountRepository.save(account);

        UserInfoUserDetails userDetails = new UserInfoUserDetails(updatedAccount);

        String jwtToken = jwtService.generateToken(userDetails);
        return AuthResponse.build(jwtToken);
    }


    public AuthResponse login(LoginRequest loginRequest){

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()
                    )
            );
            Account account = accountRepository.findByEmail(loginRequest.getEmail());
            UserInfoUserDetails userDetails = new UserInfoUserDetails(account);
            String jwtToken = jwtService.generateToken(userDetails);

            return AuthResponse.build(jwtToken);
        }catch (InternalAuthenticationServiceException e){
            throw new BadCredentialsException("Bad credentials");
        }

    }



}