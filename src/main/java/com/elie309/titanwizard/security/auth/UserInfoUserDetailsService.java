package com.elie309.titanwizard.security.auth;

import com.elie309.titanwizard.models.accountsModels.Account;
import com.elie309.titanwizard.repository.accountRepository.AccountRepository;
import com.elie309.titanwizard.security.auth.Models.UserInfoUserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserInfoUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws BadCredentialsException {
        Account account = accountRepository.findByEmail(email);
        return new UserInfoUserDetails(account);

    }
}