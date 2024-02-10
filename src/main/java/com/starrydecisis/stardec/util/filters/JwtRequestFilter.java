package com.starrydecisis.stardec.util.filters;


import com.starrydecisis.stardec.service.StarDecUserDetailsService;
import com.starrydecisis.stardec.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private StarDecUserDetailsService starDecUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

//        logger.info("JwtRequestFilter.doFilterInternal() has started");

        final String cookieHeader = request.getHeader("Cookie");
//        logger.info("cookie header = " + cookieHeader);
        // EXAMPLE:
//        Idea-3c20e728=4756e10b-cea8-4144-a301-b8bf6fd6c8b1; accessCookie=Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhIiwiZXhwIjoxNjMxNjY4NzIwLCJpYXQiOjE2MzE2MzI3MjB9.qKjXAcgsUJzhdL36cIHfvrfPbmxtNLtRU2nW5VvtlkE

        String accessTokenCookieStr = null;
        String[] cookieArr = cookieHeader == null ? new String[]{} : cookieHeader.split("; ");
        for (String cookieStr : cookieArr) {
            if (cookieStr.startsWith("accessCookie=Bearer ")) {
                accessTokenCookieStr = request.getHeader("Cookie");
//                logger.info("AUTH COOKIE FOUND, accessTokenCookieStr= " + accessTokenCookieStr);
            }
        }

        String username = null;
        String jwt = null;

        if (accessTokenCookieStr != null) {
//            logger.info("Valid Authorization Header found!");
            jwt = accessTokenCookieStr.substring(20);
//            logger.info("jwt set to " + jwt);
            username = jwtUtil.extractUsername(jwt);
        } else {
//            logger.error("NO AUTH COOKIE FOUND");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            logger.info("Starting main userValidation logic in JwetRequestFilter if block");

            UserDetails userDetails = starDecUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}