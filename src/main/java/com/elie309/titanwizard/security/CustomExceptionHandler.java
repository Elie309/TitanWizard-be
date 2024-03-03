package com.elie309.titanwizard.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail exceptionHandler(Exception ex){
        ProblemDetail errorDetails;

        if( ex instanceof BadCredentialsException){
             errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());

            errorDetails.setProperty("access_denied_reason", "Bad Credentials");

        }else if(ex instanceof AccessDeniedException){
            errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());

            errorDetails.setProperty("access_denied_reason", "Not authorized");
        }else if (ex instanceof SignatureException || ex instanceof MalformedJwtException) {
            errorDetails = ProblemDetail
                    .forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            errorDetails.setProperty("access_denied_reason", "JWT Signature not valid");
        }else if (ex instanceof ExpiredJwtException) {
            errorDetails = ProblemDetail
                    .forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            errorDetails.setProperty("access_denied_reason", "JWT Token already expired !");
        }else if(ex instanceof ResponseStatusException){
            errorDetails = ProblemDetail
                    .forStatusAndDetail(((ResponseStatusException) ex).getStatusCode(), ex.getMessage());
            errorDetails.setProperty("access_denied_reason", ex.getMessage());
        }else if (ex instanceof IllegalArgumentException){

            errorDetails = ProblemDetail
                    .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
            errorDetails.setProperty("access_denied_reason", ex.getMessage());

        }else {
            errorDetails = ProblemDetail.forStatus(500);
        }


        return errorDetails;
    }

}
