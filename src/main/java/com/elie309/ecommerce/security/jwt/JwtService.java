package com.elie309.ecommerce.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY;

    //    private final int TIME = 1000*60;

    public JwtService(Environment env) {
        this.SECRET_KEY = env.getProperty("jwt.secret");

    }

    public String generateToken(UserDetails userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generate the token
     *
     * @param extraClaims extra claims to add to our JWT Token
     * @param userDetails user details imported from Spring boot starter security
     * @return String - The JWT Generated
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {


        //IMPORTANT: For userDetails it deals with username, but it can be an email etc... in this case an email
        int TIME = 1000 * 60 * 60 * 24;
        return Jwts.
                builder() // we are calling the builder to compact later
                .setClaims(extraClaims) //Our extra claims - our extra fields
                .setSubject(userDetails.getUsername()) //USERNAME MEANS EMAIL, Spring Security uses username
                .setIssuedAt(new Date(System.currentTimeMillis())) // Current time in milliseconds
                .setExpiration(new Date(System.currentTimeMillis() + TIME)) //SETTING EXP in 24 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //DEFINING the Signing key and algorithm
                .compact(); //COMPACT will generate the token

    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /**
     * Using generics to create one function to extract any required claims
     * @return <T>
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        Claims claims = this.getAllClaims(token);
        return claimsResolver.apply(claims);

    }


    /**
     * Return all claims
     *
     * @return Claims
     */
    private Claims getAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Get the key necessary for encoding and decoding our key
     *
     * @return Key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
