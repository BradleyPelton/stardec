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

        logger.info("JwtRequestFilter.doFilterInternal() has started");

        // TODO - This is probably going to break once more cookies are added
        // TODO - Refactor
        final String authorizationHeader = request.getHeader("Cookie");
        logger.info("cookie header = " + authorizationHeader);
//        Cookie[] currentCookies = request.getCookies();
//        String authorizationHeader  = null;
//        if (!(currentCookies == null || currentCookies.length == 0)) {
//            for (Cookie cookie : currentCookies) {
//                logger.info(cookie.getName());
//                if (cookie.getName() == "accessCookie") {
//                    authorizationHeader = cookie.getValue();
//                }
//            }
//        }
        if (authorizationHeader == null) {
            logger.info("NO AUTH COOKIE FOUND");
        } else {
            logger.info("AUTH COOKIE = " + authorizationHeader);
        }


        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("accessCookie=Bearer ")) {
            logger.info("Valid Authorization Header found!");
            jwt = authorizationHeader.substring(20);
            logger.info("jwt set to " + jwt);
            username = jwtUtil.extractUsername(jwt);
        } else {
            logger.info("No Authorization Header found!");
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("Starting major if in doFilterInternal");

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