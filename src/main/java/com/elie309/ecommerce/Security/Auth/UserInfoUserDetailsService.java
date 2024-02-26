package com.elie309.ecommerce.Security.Auth;

import com.elie309.ecommerce.Models.AccountsModels.Account;
import com.elie309.ecommerce.Repository.AccountRepository.AccountRepository;
import com.elie309.ecommerce.Security.Auth.Models.UserInfoUserDetails;
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