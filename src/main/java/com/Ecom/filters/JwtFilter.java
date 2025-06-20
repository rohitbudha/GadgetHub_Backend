package com.Ecom.filters;
import com.Ecom.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;



        // Check if the Authorization header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extract token
            username = jwtUtil.extractUsername(jwt); // Extract username from token

        }

        // If username is extracted from token and token is valid, authenticate user
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("Authorities from token: " + userDetails.getAuthorities());
            System.out.println("Request Path: " + request.getRequestURI());
            System.out.println("JWT Token: " + jwt);

            if (jwtUtil.validateToken(jwt)) {
                // Create an authentication token for the user
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Set additional details like IP, session ID, etc.

                // Set the authentication token in the security context
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Continue with the next filter
        chain.doFilter(request, response);
    }
}

//
//package com.Ecom.filters;
//
//import com.Ecom.utils.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    // List of public paths to skip JWT validation
//    private static final List<String> PUBLIC_PATHS = List.of(
//            "/customers/cget", "/customers/category", "/api/auth/register"
//    );
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//
//        // Skip JWT validation for public endpoints
//        if (PUBLIC_PATHS.stream().anyMatch(path::startsWith)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String authorizationHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwt = null;
//
//        // Check if the Authorization header is present and starts with "Bearer "
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7); // Extract token
//            username = jwtUtil.extractUsername(jwt); // Extract username from token
//        }
//
//        // If username is extracted from token and token is valid, authenticate user
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtUtil.validateToken(jwt)) {
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//
//        // Continue with the next filter
//        chain.doFilter(request, response);
//    }
//}
