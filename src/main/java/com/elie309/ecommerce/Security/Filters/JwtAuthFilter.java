package com.elie309.ecommerce.Security.Filters;

import com.elie309.ecommerce.Security.Auth.UserInfoUserDetailsService;
import com.elie309.ecommerce.Security.JWT.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final HandlerExceptionResolver resolver;
    private final UserInfoUserDetailsService userDetailsService;


    public JwtAuthFilter(JwtService jwtService,@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, UserInfoUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.resolver = resolver;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Getting and handling the header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); //7 represent the number of letter of Bearer with the last space

        if (request.getRequestURI().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        //This try and catch will handle if the token is expired
        try {


            userEmail = jwtService.extractUsername(jwt); //Which means here the email

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
            filterChain.doFilter(request, response);


        } catch (Exception e){
            resolver.resolveException(request,response, null, e);
        }

    }

}
