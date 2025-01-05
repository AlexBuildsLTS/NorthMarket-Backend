package com.northmarket.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class JwtAuthUtil {
    public static void setAuthentication(UserDetails userDetails, HttpServletRequest req) {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
