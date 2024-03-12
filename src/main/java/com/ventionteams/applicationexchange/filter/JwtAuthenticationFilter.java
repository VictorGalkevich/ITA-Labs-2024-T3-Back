package com.ventionteams.applicationexchange.filter;

import com.ventionteams.applicationexchange.dto.UserAuthDto;
import com.ventionteams.applicationexchange.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService service;
    public static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = req.getHeader("Authorization");

        if (authHeaderIsInvalid(authorizationHeader)) {
            filterChain.doFilter(req, resp);
            return;
        }

        String token = authorizationHeader.replace(TOKEN_PREFIX, "");
        UserAuthDto userPrincipal = service.parseToken(token);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                userPrincipal,
                null,
                userPrincipal.authorities()
        ));
    }

    private static boolean authHeaderIsInvalid(String authorizationHeader) {
        return authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX);
    }
}
