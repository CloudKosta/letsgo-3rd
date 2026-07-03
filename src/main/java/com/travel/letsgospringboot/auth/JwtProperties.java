package com.travel.letsgospringboot.auth;

public interface JwtProperties {
    String SECRET = "cloudMunskksa2412knalsjf1985ur89qewyut98y426htqidhjia0suir9i012n4ekaklsjgfkl";
    int EXPIRATION_TIME = 1000 * 60 * 60;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
