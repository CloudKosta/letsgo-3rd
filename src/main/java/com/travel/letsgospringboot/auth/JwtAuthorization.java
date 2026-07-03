package com.travel.letsgospringboot.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.travel.letsgospringboot.user.repository.JpaUsers;
import com.travel.letsgospringboot.user.repository.UserJpaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorization extends BasicAuthenticationFilter {
    UserJpaRepository userJpaRepository;

    public JwtAuthorization(AuthenticationManager authenticationManager, UserJpaRepository userJpaRepository) {
        super(authenticationManager);
        this.userJpaRepository = userJpaRepository;
    }


    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String jwtToken = token.substring(JwtProperties.TOKEN_PREFIX.length());
        String username = null;
        try {
            username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes())).build().verify(jwtToken).getSubject();
            if(username != null){
                JpaUsers jpaUsers = userJpaRepository.findByUserID(username);
                if (jpaUsers != null) {
                    AppUserDetails appUserDetails = new AppUserDetails(jpaUsers);
                    Authentication auth = new UsernamePasswordAuthenticationToken(appUserDetails, null, appUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (JWTVerificationException e) {
            logger.warn("JWT 검증 실패: " + e.getMessage());
}

        chain.doFilter(request, response);
    }
}
