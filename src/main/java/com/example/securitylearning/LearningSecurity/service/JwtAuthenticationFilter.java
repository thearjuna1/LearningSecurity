package com.example.securitylearning.LearningSecurity.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
private  final JwtService jwtService;
private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); //get header

        if (authHeader != null || authHeader.startsWith("Bearer ")) { //validate the header
            filterChain.doFilter(request,response);
            return;
        }
        String jwt = authHeader.substring(7); //get token

        String username = jwtService.getUsername(authHeader); //extract the username from the token


            if (username != null  && SecurityContextHolder.getContext().getAuthentication() == null) { //check username and authentication

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if(jwtService.isTokenValid(jwt , userDetails)){ //validate token

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, //create new authentication Object
                            null, userDetails.getAuthorities());

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().
                                    buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication); //set the user to authenticated
                }
            }


    }
}
