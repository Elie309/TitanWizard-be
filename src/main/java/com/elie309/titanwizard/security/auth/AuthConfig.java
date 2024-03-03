package com.elie309.titanwizard.security.auth;

import com.elie309.titanwizard.repository.accountRepository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AuthConfig {


    private final AccountRepository accountRepository;

    public AuthConfig(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Setting the user details service by find the mail
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService(accountRepository);
    }

    /**
     * Bean will the user details service and the password encoder
     * @return Auth provider
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Method will handle how the password is encoded
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
