package com.deploy.playtogether.config.jwt;

public interface JwtService {
    String issuedToken(String subject, String role, long periodSecond);

    boolean verifyToken(String token);

    String getSubject(String token);

}
