package com.yurdm.qualifications.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.subject}")
    private String subject;

    @Value("${jwt.expireMinutes}")
    private int expireMinutes;

    public String generateToken(String email, String firstName, String lastName, String role) {
        Date expireAt = Date.from(ZonedDateTime.now().plusMinutes(expireMinutes).toInstant());

        return JWT.create()
                .withSubject(subject)
                .withClaim("email", email)
                .withClaim("firstName", firstName)
                .withClaim("lastName", lastName)
                .withClaim("role", role)
                .withIssuedAt(Instant.now())
                .withExpiresAt(expireAt)
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(secret));
    }

    private DecodedJWT getDecoded(String token) {
        var verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(issuer)
                .build();

        return verifier.verify(token);
    }

    public String retrieveClaim(String token, String claim) throws JWTVerificationException {
        DecodedJWT jwt = getDecoded(token);
        return jwt.getClaim(claim).asString();
    }

    public Date getExpireDate(String token) {
        DecodedJWT jwt = getDecoded(token);
        return jwt.getExpiresAt();
    }
}
