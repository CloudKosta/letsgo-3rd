package com.travel.letsgospringboot.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.letsgospringboot.user.vo.UserVO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response
            , FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AppUserDetails appUserDetails = (AppUserDetails) authResult.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject(appUserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", appUserDetails.getUser().getId())
                .withClaim("username", appUserDetails.getUser().getName())
                .withClaim("email", appUserDetails.getUser().getEmail())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(new ObjectMapper().writeValueAsString(Map.of("message", "OK")));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UserVO userVO = new ObjectMapper().readValue(request.getInputStream(), UserVO.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userVO.getUserID(), userVO.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            throw new AuthenticationServiceException("로그인 요청 본문을 읽을 수 없습니다.", e);
        }
    }
}
